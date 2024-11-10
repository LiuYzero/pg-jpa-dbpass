package com.springbootwork.pg_jpa_dbpaas.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="video_caption")
public class VideoCaptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String video;

    @Column(columnDefinition = "text")
    private String caption;

    @Column(columnDefinition = "text", name="store_path")
    private String storePath;

    @Column(columnDefinition = "date",name = "created_at")
    private LocalDate date;

    @Override
    public String toString() {
        return "VideoCaptions{" +
                "id=" + id +
                ", content='" + caption + '\'' +
                ", storePath='" + storePath + '\'' +
                ", date=" + date +
                '}';
    }
}
