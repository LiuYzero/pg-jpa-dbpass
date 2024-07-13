package com.springbootwork.pg_jpa_dbpaas.scheduler;

import com.springbootwork.pg_jpa_dbpaas.service.TestService;
import com.springbootwork.pg_jpa_dbpaas.service.rss.CCTVNewsServices;
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

    @Resource
    CCTVNewsServices cctvNewsServices;

    @Scheduled(cron="59 * *  * * ? ")
    public void saveDateToTest(){
        String id = service.saveContent();
        LOGGER.info("save id {}", id);
    }

    @Scheduled(cron = "0 50 0/2 * * ? ")
    public void recordCCTVNewsScheduled(){
        cctvNewsServices.work();
        LOGGER.info("executed recordCCTVNewsScheduled");
    }
}
