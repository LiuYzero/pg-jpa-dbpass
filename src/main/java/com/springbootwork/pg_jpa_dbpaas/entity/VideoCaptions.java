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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

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
