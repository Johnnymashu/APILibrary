package com.example.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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
    private Long id;
    private String author;
    private String title;
    private String genre;

    public Book(String author, String title, String genre){
        this.author = author;
        this.title = title;
        this.genre = genre;
        borrowedBy = new ArrayList<>();
    }

    @JsonBackReference
    @ManyToOne
            //@Cascade(value = CascadeType.ALL)
    Publisher releaser;

    @JsonBackReference
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    User user;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "borrowed_by_id")
    private List<User> borrowedBy;






}
