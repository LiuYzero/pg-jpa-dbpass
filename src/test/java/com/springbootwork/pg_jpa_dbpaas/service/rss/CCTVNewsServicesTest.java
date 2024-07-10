package com.springbootwork.pg_jpa_dbpaas.service.rss;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CCTVNewsServicesTest {

    @Autowired
    CCTVNewsServices services;

    @Test
    void rssParse() {
        Boolean recordFlag = services.recordNews();
        System.out.println("recordFlag = " + recordFlag);

        Assert.isTrue(recordFlag, "recordFlag = " + recordFlag);
    }
}