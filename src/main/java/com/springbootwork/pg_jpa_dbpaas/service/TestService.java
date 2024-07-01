package com.springbootwork.pg_jpa_dbpaas.service;

import com.springbootwork.pg_jpa_dbpaas.entity.ContentEntity;
import com.springbootwork.pg_jpa_dbpaas.repository.TestRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class TestService {
    public  static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);
    @Autowired
    TestRepository repository;

    public String saveContent(){
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setContent(LocalDate.now().toString());
//        contentEntity.setId(new Random().nextLong());
        repository.save(contentEntity);
        LOGGER.info("save {}", contentEntity);
        return String.valueOf(contentEntity.getId());
    }
}
