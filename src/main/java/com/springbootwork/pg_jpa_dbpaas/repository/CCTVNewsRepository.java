package com.springbootwork.pg_jpa_dbpaas.repository;

import com.springbootwork.pg_jpa_dbpaas.entity.CCTVNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CCTVNewsRepository extends JpaRepository<CCTVNewsEntity, Integer> {
    List<CCTVNewsEntity> findAllByDate(LocalDate date);
}
