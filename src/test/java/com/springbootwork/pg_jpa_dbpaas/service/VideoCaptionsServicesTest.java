package com.springbootwork.pg_jpa_dbpaas.service;

import com.springbootwork.pg_jpa_dbpaas.entity.VideoCaptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class VideoCaptionsServicesTest {


    @Autowired
    VideoCaptionsServices services;

    @Test
    void findTodayCaptions() {
        List<VideoCaptions> todayCaptions = services.findByDate(LocalDate.now());
        boolean flag = !todayCaptions.isEmpty();
        Assert.isTrue(flag, "todayCaptions = " + todayCaptions);

    }
}