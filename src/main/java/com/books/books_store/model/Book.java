package com.books.books_store.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String slug;
    @Column(name = "description")
    private String desc;
    private Float price;

    @DateTimeFormat (iso = ISO.DATE_TIME)
    private LocalDateTime createdAt;
    @DateTimeFormat (iso = ISO.DATE_TIME)
    private LocalDateTime updatedAt;

    @PrePersist
    void iniCreatedAt(){
       createdAt= LocalDateTime.now();
    }

    @PreUpdate
    void iniUpdatedAt(){
        updatedAt=LocalDateTime.now();
    }

}
