package com.springbootwork.pg_jpa_dbpaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.springbootwork.pg_jpa_dbpaas.entity.ResponseResult;
import com.springbootwork.pg_jpa_dbpaas.service.business.ResourceServices;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/pg")
public class AllController {
    private static final Logger logger = LoggerFactory.getLogger(AllController.class);

    @Resource
    ResourceServices resourceServices;


    @PostMapping(path = "/VideoCaptions/insert")
    public ResponseResult insertVideoCaptions(JSONObject data){
        logger.info("enter insertVideoCaptions {}.",data);

        return ResponseResult.success();
    }

    @GetMapping(path = "/resources/query")
    public ResponseResult queryResources(){
        JSONObject data = resourceServices.queryResourceInfo();
        ResponseResult responseResult = ResponseResult.success();
        responseResult.setData(data);
        return responseResult;
    }
}
