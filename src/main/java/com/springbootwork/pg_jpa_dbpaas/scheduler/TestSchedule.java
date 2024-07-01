package com.springbootwork.pg_jpa_dbpaas.scheduler;

import com.springbootwork.pg_jpa_dbpaas.service.TestService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSchedule {
    public  static final Logger LOGGER = LoggerFactory.getLogger(TestSchedule.class);
    @Resource
    TestService service;

    @Scheduled(cron="0/5 * *  * * ? ")
    public void saveDateToTest(){
        String id = service.saveContent();
        LOGGER.info("save id {}", id);
    }
}
