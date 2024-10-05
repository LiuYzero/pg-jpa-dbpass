package com.springbootwork.pg_jpa_dbpaas.repository;

import com.springbootwork.pg_jpa_dbpaas.entity.VideoCaptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VideoCaptionsRepository extends JpaRepository<VideoCaptions, Integer> {

    List<VideoCaptions> findAllByDate(LocalDate date);
}
