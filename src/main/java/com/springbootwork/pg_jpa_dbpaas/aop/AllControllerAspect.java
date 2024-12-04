package com.springbootwork.pg_jpa_dbpaas.aop;


import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Aspect
@Component
public class AllControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(AllControllerAspect.class);

    private static final String INFLUXDB_URL = "http://IOT-INFLUXDB-API/influxdb/insertData";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Pointcut("execution(public * com.springbootwork.pg_jpa_dbpaas.controller.AllController.*(..))")
    public void webLog(){}

    public void doBefore(){

    }

    @Around("webLog()")
    public Object recordCostTime(ProceedingJoinPoint joinPoint) {
        Long startTime2 = System.currentTimeMillis();

        Object result = "";
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error("error: ", e);
        }finally {
            logger.info("recordCostTime : {} ms", System.currentTimeMillis() - startTime2);
            Long costTime = System.currentTimeMillis() - startTime2;

            JSONObject interfaceData = new JSONObject();
            interfaceData.put("database", "db_iot");
            interfaceData.put("table","t_interface");
            JSONObject tags = new JSONObject();
            tags.put("interface", environment.getProperty("spring.application.name")+"/controller/AllController."+joinPoint.getSignature().getName());

            JSONObject fields = new JSONObject();
            fields.put("costTime", costTime);

            interfaceData.put("tags", tags);
            interfaceData.put("fields", fields);

            logger.info("aop minioData: {}", interfaceData);
            JSONObject response = restTemplate.postForObject(INFLUXDB_URL, interfaceData, JSONObject.class);
            logger.info("response: {}", response);

        }

        return result;
    }

}
