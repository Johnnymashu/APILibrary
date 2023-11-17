package com.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue
    private Integer id;
    private String author;
    private String title;
    private String genre;

    public Books(String author, String title, String genre){
        this.author = author;
        this.title = title;
        this.genre = genre;

    }
    private Publisher publisher;





}
