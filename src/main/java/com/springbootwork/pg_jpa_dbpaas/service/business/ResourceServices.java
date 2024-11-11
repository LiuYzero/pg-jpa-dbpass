package com.springbootwork.pg_jpa_dbpaas.service.business;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springbootwork.pg_jpa_dbpaas.entity.CCTVNewsEntity;
import com.springbootwork.pg_jpa_dbpaas.entity.VideoCaptions;
import com.springbootwork.pg_jpa_dbpaas.service.VideoCaptionsServices;
import com.springbootwork.pg_jpa_dbpaas.service.rss.CCTVNewsServices;
import jakarta.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 提供混合资源信息
 */
@Service
public class ResourceServices {

    @Resource
    CCTVNewsServices cctvNewsServices;

    @Resource
    VideoCaptionsServices videoCaptionsServices;


    public JSONObject queryResourceInfo(){
        LocalDate yesterday  = LocalDate.now().minusDays(1l);
        List<VideoCaptions> videoCaptionsList = videoCaptionsServices.findByDate(yesterday);
        List<CCTVNewsEntity> cctvNewsEntityList = cctvNewsServices.findAllByDate(yesterday);

        JSONObject result = new JSONObject();

        String title = "";
        for(VideoCaptions videoCaption : videoCaptionsList){
            if(!StringUtils.equals(title,videoCaption.getVideo())){
                title = videoCaption.getVideo();
                result.put(videoCaption.getVideo(), new JSONArray());
            }
            result.getJSONArray(videoCaption.getVideo()).add(videoCaption.getCaption());
        }

        result.put("cctvNews", new JSONArray());
        for(CCTVNewsEntity cctvNewsEntity : cctvNewsEntityList){
            result.getJSONArray("cctvNews").add(cctvNewsEntity.getContent());
        }

        return result;
    }
}
