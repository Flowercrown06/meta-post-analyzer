package com.nracademy.metapostanalyzer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    private String id;
    private String message;
    private LocalDate createdTime;
    private int likesCount;
    private int commentsCount;

    public int getEngagement() {
        return this.likesCount + this.commentsCount;
    }
}