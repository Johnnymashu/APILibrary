package com.example.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    private String author;
    private String title;
    private String genre;

    public Book(Integer id, String author, String title, String genre){
        this.author = author;
        this.title = title;
        this.genre = genre;

    }
    @JsonBackReference
    @ManyToOne
    Publisher releaser;
//            (cascade = CascadeType.PERSIST)
//    private Publisher publisher;







}
