package com.springbootwork.pg_jpa_dbpaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.springbootwork.pg_jpa_dbpaas.entity.ResponseResult;
import com.springbootwork.pg_jpa_dbpaas.entity.TranslateEntity;
import com.springbootwork.pg_jpa_dbpaas.service.TranslateService;
import com.springbootwork.pg_jpa_dbpaas.service.business.ResourceServices;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/pg")
public class AllController {
    private static final Logger logger = LoggerFactory.getLogger(AllController.class);

    @Resource
    ResourceServices resourceServices;

    @Resource
    TranslateService translateService;


    @PostMapping(path = "/VideoCaptions/insert")
    public ResponseResult insertVideoCaptions(@RequestBody JSONObject data){
        logger.info("enter insertVideoCaptions {}.",data);
        translateService.save(data);
        return ResponseResult.success();
    }

    @GetMapping(path = "/resources/query")
    public ResponseResult queryResources(){
        JSONObject data = resourceServices.queryResourceInfo();
        ResponseResult responseResult = ResponseResult.success();
        responseResult.setData(data);
        return responseResult;
    }

    @PostMapping(path = "/translate/save")
    public ResponseResult saveTranslate(@RequestBody JSONObject data){
        logger.info("enter saveTranslate {}.",data);
        TranslateEntity translate = data.toJavaObject(TranslateEntity.class);
        translateService.save(translate);
        return ResponseResult.success();
    }

    @PostMapping(path = "/translate/queryByDate")
    public ResponseResult queryTranslate(@RequestBody JSONObject data){
        logger.info("enter queryTranslate {}.",data);
        List<TranslateEntity> translateEntities = translateService.findByDate(data);
        return ResponseResult.success(translateEntities);
    }
}
