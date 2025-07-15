package com.springbootwork.pg_jpa_dbpaas.service;

import com.alibaba.fastjson.JSONObject;
import com.springbootwork.pg_jpa_dbpaas.entity.TranslateEntity;
import com.springbootwork.pg_jpa_dbpaas.entity.VideoCaptions;
import com.springbootwork.pg_jpa_dbpaas.repository.TranslateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 翻译表表的服务
 * @author zhou
 * @version 1.0
 */
@Service
public class TranslateService {
    
    public static final Logger logger = LoggerFactory.getLogger(TranslateService.class);
    
    
    @Autowired
    private TranslateRepository translateRepository;
    
    
    public boolean save(JSONObject data){
        logger.info("save translate {} start.", data);
        TranslateEntity translate = data.toJavaObject(TranslateEntity.class);
        translate.setCreateTime(LocalDate.now());
        return save(translate);
    }

    public boolean save(TranslateEntity translate){
        logger.info("save translate {} start.", translate);

        translate.setCreateTime(LocalDate.now());
        TranslateEntity save = translateRepository.save(translate);
        logger.info("save translate {} success.", save);

        return true;
    }

    public List<TranslateEntity> findByDate(JSONObject data){
        String dateStr = data.getString("createTime");
        // 定义日期时间格式（匹配 "yyyy/MM/dd HH:mm:ss"）
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        // 解析字符串并转为 LocalDate（自动丢弃时间部分）
        LocalDate localDate = LocalDate.parse(dateStr, formatter);

        List<TranslateEntity> allByDate = translateRepository.findAllByCreateTime(localDate);
        logger.info("find all by date {} success.", localDate);
        return allByDate;
    }
}
