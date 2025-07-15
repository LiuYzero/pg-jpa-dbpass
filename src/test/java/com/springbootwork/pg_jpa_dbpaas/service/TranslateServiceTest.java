package com.springbootwork.pg_jpa_dbpaas.service;

import com.springbootwork.pg_jpa_dbpaas.entity.TranslateEntity;
import com.springbootwork.pg_jpa_dbpaas.repository.TranslateRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class TranslateServiceTest {

    @Autowired
    TranslateRepository transRepository;

    @Resource
    TranslateService translateService;

    private static Long id;

    @Test
    public void testSave(){
        TranslateEntity translate = new TranslateEntity("house","房屋");
        translate.setCreateTime(LocalDate.now());
        TranslateEntity save = transRepository.save(translate);
        if(save.getId() != null){
            id = save.getId();
        }
        Assert.notNull(save.getId(),"save = " + save);
    }


    @Test
    public void testDeteletById(){
        if(id != null){
            TranslateEntity translate = new TranslateEntity();
            translate.setId(id);
            transRepository.delete(translate);
        }
        Assert.isTrue(true,"delete success");
    }

    @Test
    public void testFindByILocalDate(){

        TranslateEntity translate = new TranslateEntity();
        translate.setCreateTime(LocalDate.now());
        List<TranslateEntity> allByDate = transRepository.findAllByCreateTime(LocalDate.now());
        Assert.isTrue(!allByDate.isEmpty(),"find by LocalDate size {} " + allByDate.size());

    }


}
