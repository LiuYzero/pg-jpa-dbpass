package com.springbootwork.pg_jpa_dbpaas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="test")
public class ContentEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

//    @Lob
    @Column(columnDefinition = "text")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContentEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
