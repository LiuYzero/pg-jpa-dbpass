package com.springbootwork.pg_jpa_dbpaas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * 翻译的数据表的实体类
 *
 * @author liuyang
 * @since  2025/07/15
 */
@Data
@Entity
@Table(name="translate")
public class TranslateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "translate_seq")
    @SequenceGenerator(name = "translate_seq", sequenceName = "translate_id_seq")
    Long id;

    @Column(columnDefinition = "text")
    String key;

    @Column(columnDefinition = "text")
    String value;

    LocalDate createTime;

    public TranslateEntity(Long id, String key, String value, LocalDate createTime) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.createTime = createTime;
    }

    public TranslateEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public TranslateEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }
}
