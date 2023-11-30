package com.example.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(
            name = "book_seq",
            sequenceName = "book_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "book_id")
    private Long id;
    private String author;
    private String title;
    private String genre;

    @OneToMany
    @JoinTable(name = "USER_BOOKS",
            joinColumns = {@JoinColumn(name = "book_id", insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", insertable = false, updatable = false)})
    private List<User> users;
    public Book(String author, String title, String genre){
        this.author = author;
        this.title = title;
        this.genre = genre;
        users = new ArrayList<>();
    }

   // @JsonBackReference
//    @ManyToOne
//            //@Cascade(value = CascadeType.ALL)
//    Publisher releaser;

    //@JsonBackReference(value = "user_books")







}
