package com.springbootwork.pg_jpa_dbpaas.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/pg")
public class AllController {
    private static final Logger logger = LoggerFactory.getLogger(AllController.class);


    @PostMapping(path = "/VideoCaptions/insert")
    public JSONObject insertVideoCaptions(JSONObject data){
        logger.info("enter insertVideoCaptions {}.",data);

        return new JSONObject();
    }
}
