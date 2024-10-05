package com.springbootwork.pg_jpa_dbpaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springbootwork.pg_jpa_dbpaas.entity.VideoCaptions;
import com.springbootwork.pg_jpa_dbpaas.repository.VideoCaptionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoCaptionsServices {

    private static final Logger logger = LoggerFactory.getLogger(VideoCaptionsServices.class);

    @Autowired
    VideoCaptionsRepository videoCaptionsRepository;

    public void work(JSONObject data){
        logger.info("data {}", data);
    }

    public void work(List<VideoCaptions> videoCaptionsList){
        for(VideoCaptions videoCaptions : videoCaptionsList){
            work(videoCaptions);
        }
    }

    public void work(VideoCaptions videoCaptions){
        videoCaptionsRepository.save(videoCaptions);
        logger.info("save video captions {} success.", JSON.toJSONString(videoCaptions));

    }

}