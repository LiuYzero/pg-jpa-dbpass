package com.springbootwork.pg_jpa_dbpaas.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="videos_captions")
public class VideoCaptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String content;

    @Column(columnDefinition = "text", name="store_path")
    private String storePath;

    @Column(columnDefinition = "date")
    private LocalDate date;

    @Override
    public String toString() {
        return "VideoCaptions{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", storePath='" + storePath + '\'' +
                ", date=" + date +
                '}';
    }
}
