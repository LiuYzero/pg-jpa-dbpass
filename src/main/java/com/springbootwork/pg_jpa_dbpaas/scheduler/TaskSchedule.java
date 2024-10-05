package com.springbootwork.pg_jpa_dbpaas.scheduler;

import com.springbootwork.pg_jpa_dbpaas.monitor.IotMonitorServices;
import com.springbootwork.pg_jpa_dbpaas.service.TestService;
import com.springbootwork.pg_jpa_dbpaas.service.rss.CCTVNewsServices;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedule {
    public  static final Logger LOGGER = LoggerFactory.getLogger(TaskSchedule.class);
    @Resource
    TestService service;
    @Resource
    CCTVNewsServices cctvNewsServices;
    @Resource
    IotMonitorServices iotMonitorServices;

    @Scheduled(cron="0 */10 *  * * ? ")
    public void saveDateToTest(){
        String id = service.saveContent();
    }

    @Scheduled(cron = "0 20 0/2 * * ? ")
    public void recordCCTVNewsScheduled(){
        cctvNewsServices.work();
        LOGGER.info("executed recordCCTVNewsScheduled");
    }

    @Scheduled(cron="*/10 * *  * * ?")
    public void monitorReport(){
        iotMonitorServices.monitorReport();
        LOGGER.info("executed iotMonitorServices.monitorReport();");
    }

}
