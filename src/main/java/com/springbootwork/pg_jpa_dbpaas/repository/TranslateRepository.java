package com.springbootwork.pg_jpa_dbpaas.repository;

import com.springbootwork.pg_jpa_dbpaas.entity.TranslateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 翻译表的查询服务
 *
 * @author liuyang
 * @since  2025/07/15
 */
@Repository
public interface TranslateRepository extends JpaRepository<TranslateEntity, Integer> {

    List<TranslateEntity> findAllByCreateTime(LocalDate createTime);
}
