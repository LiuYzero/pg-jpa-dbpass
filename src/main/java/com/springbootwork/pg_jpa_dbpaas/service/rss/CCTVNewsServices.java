package com.springbootwork.pg_jpa_dbpaas.service.rss;

import com.springbootwork.pg_jpa_dbpaas.service.TestService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public Boolean recordNews(){
        String news = getCCTVNews();
        List<String> newList = rssParse(news);
        if(newList.isEmpty()){
            LOGGER.warn("news is empty at today");
            return Boolean.FALSE;
        }
        save2Table(newList);

        LOGGER.info("record new success");
        return Boolean.TRUE;
    }

    public boolean save2Table(List<String> newList){
        for(String content : newList){
            service.saveContent(content);
        }
        return Boolean.TRUE;
    }

    public List<String> rssParse(String news){
        List<String> newList = new ArrayList<>();
        String content = news;
        if(content.isBlank()){
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
        if(!isToday(newList.get(0).substring(0,8))){
            newList = List.of();
        }
        LOGGER.info("done of parse news");
        return newList;
    }

    public String getCCTVNews() {
        return restTemplate.getForObject(cctvNewRssUrl, String.class);
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
}
