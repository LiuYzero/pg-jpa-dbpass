package com.springbootwork.pg_jpa_dbpaas.service.rss;

import com.springbootwork.pg_jpa_dbpaas.entity.CCTVNewsEntity;
import com.springbootwork.pg_jpa_dbpaas.repository.CCTVNewsRepository;
import com.springbootwork.pg_jpa_dbpaas.service.TestService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 新闻联播RSS订阅解析
 */
@Service
public class CCTVNewsServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(CCTVNewsServices.class);
    String cctvNewRssUrl = "http://mrxwlb.com/feed/";
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    TestService service;
    @Autowired
    CCTVNewsRepository newsRepository;


    public List<CCTVNewsEntity> findAllByDate(LocalDate date){
        List<CCTVNewsEntity> cctvNewsEntityList = newsRepository.findAllByDate(date);
        LOGGER.info("query yesterday news, find {} record.", cctvNewsEntityList.size());
        return cctvNewsEntityList;
    }


    public void work(){
        // 如果昨天的新闻尚未记录，就记录
        if(!chechYesterdayNews()){
            LOGGER.info("will record yesterday news.");
            recordNews();
            LOGGER.info("recorded yesterday news.");
        }else{
            LOGGER.info("yesterday news has been recorded.");
        }
    }

    public Boolean chechYesterdayNews(){
        // 查询是否已记录昨天的新闻数据
        List<CCTVNewsEntity> cctvNewsEntityList = newsRepository.findAllByDate(LocalDate.now().minusDays(1l));
        LOGGER.info("query yesterday news, find {} record.", cctvNewsEntityList.size());
        if(cctvNewsEntityList.isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void recordNews(){
        String news = getCCTVNews();
        List<String> newList = rssParse(news);
        if(newList.isEmpty()){
            LOGGER.warn("news parse failed");
            return;
        }
        save2Table(newList);

        LOGGER.info("record new success");
    }

    public Boolean recordNewsTest(){
        String news = getCCTVNews();
        List<String> newList = rssParse(news);
        if(newList.isEmpty()){
            LOGGER.warn("news is empty at today");
            return Boolean.FALSE;
        }
        save2TableTest(newList);

        LOGGER.info("record new success");
        return Boolean.TRUE;
    }

    public boolean save2Table(List<String> newList){
        LocalDate yesterdayDate = LocalDate.now().minusDays(1l);
        for(String content : newList){
//            service.saveContent(content);
            CCTVNewsEntity entity = new CCTVNewsEntity();
            entity.setContent(content);
            entity.setDate(yesterdayDate);
            newsRepository.save(entity);
        }
        return Boolean.TRUE;
    }

    public void save2TableTest(List<String> newList){
        for(String content : newList){
            service.saveContent(content);
        }
    }

    public List<String> rssParse(String news){
        List<String> newList = new ArrayList<>();
        if(news.isBlank()){
            return newList;
        }

        Document document = null;
        try {
            document = DocumentHelper.parseText(news);
        } catch (DocumentException e) {
            LOGGER.error("解析 {} 出错 {}.", cctvNewRssUrl, e.getMessage());
        }
        if(document == null){
            return newList;
        }

        Element rssRoot = document.getRootElement();
        List<Element> items = rssRoot.element("channel").elements("item");
        if(items.isEmpty()){
            LOGGER.warn("no item in rss {}", cctvNewRssUrl);
            return  newList;
        }

        Element firstItem = items.get(0);

        Element contentEncoded = (Element) firstItem.selectSingleNode("//content:encoded");

        String tmpList =  contentEncoded.getTextTrim().replaceAll("</strong>","")
                .replaceAll("<strong>","")
                .replaceAll("<ul>","")
                .replaceAll("</ul>","")
                .replaceAll("<p>","")
                .replaceAll("</p>","br")
                .replaceAll("<li>","")
                .replaceAll("</li>","br")
                .replaceAll("&ldquo;","")
                .replaceAll("&rdquo;","")
                .replaceAll("&nbsp;","")
                .replaceAll("&nbsp;","")
                .replaceAll("&middot;","")
                .replaceAll("&mdash;","")
                .replaceAll("\\s", "");;

        List<String> tmpArray = List.of(tmpList.split("br"));
        for(String str : tmpArray){
            if(str.isEmpty()){
                continue;
            }
            LOGGER.info(str);
            newList.add(str);
        }
        if(!isYesterday(newList.get(0).substring(0,8))){
            newList = List.of();
        }
        LOGGER.info("done of parse news");
        return newList;
    }

//    public String getCCTVNews() {
//        return restTemplate.getForObject(cctvNewRssUrl, String.class);
//    }

    public String getCCTVNews(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://mrxwlb.com/feed/")
                .method("GET", null)
                .addHeader("Cookie", "security_session_verify=1e5eaf6ecb787e551ce8e6f47663ade4")
                .build();
        String content = "";
        try {
            Response response = client.newCall(request).execute();
            content = response.body().string();
        } catch (IOException e) {
//            throw new RuntimeException(e);
            LOGGER.warn("okhttp execute failed.");
            LOGGER.warn("{}", e);
        }
        return content;
    }

    private boolean isToday(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 将字符串转换为LocalDate对象
        LocalDate targetDate = LocalDate.parse(dateStr, formatter);

        // 获取当前日期
        LocalDate today = LocalDate.now().minusDays(0l);

        // 比较日期
        if (targetDate.equals(today)) {
            LOGGER.info("is today");
            return Boolean.TRUE;
        } else {
            LOGGER.info("is not today");
            return Boolean.FALSE;
        }
    }

    private boolean isYesterday(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 将字符串转换为LocalDate对象
        LocalDate targetDate = LocalDate.parse(dateStr, formatter);

        // 获取昨天日期
        LocalDate yesterday = LocalDate.now().minusDays(1l);

        // 比较日期
        if (targetDate.equals(yesterday)) {
            LOGGER.info("is yesterday");
            return Boolean.TRUE;
        } else {
            LOGGER.info("is not yesterday");
            return Boolean.FALSE;
        }
    }
}
