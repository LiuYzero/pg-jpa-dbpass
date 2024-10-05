package com.springbootwork.pg_jpa_dbpaas.service.rss;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CCTVNewsServicesTest {
    @Autowired
    CCTVNewsServices services;

    @Test
    void rssParse() {
        Boolean recordFlag = services.recordNewsTest();

//        Boolean recordFlag = true;
//        services.recordNews();

        System.out.println("recordFlag = " + recordFlag);

        Assert.isTrue(recordFlag, "recordFlag = " + recordFlag);
    }

}