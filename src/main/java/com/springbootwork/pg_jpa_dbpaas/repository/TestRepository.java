package com.springbootwork.pg_jpa_dbpaas.repository;

import com.springbootwork.pg_jpa_dbpaas.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<ContentEntity,Integer> {
}
