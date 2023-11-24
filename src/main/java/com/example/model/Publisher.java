package com.example.model;

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
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_seq")
    @SequenceGenerator(
            name = "publisher_seq",
            sequenceName = "publisher_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;
    private String brand;
    private Long prestige;

    @JsonManagedReference
    @OneToMany(mappedBy = "releaser")
    private List<Book> releasedBooks;


    public Publisher(String brand, long prestige){
        this.brand = brand;
        this.prestige = prestige;
        releasedBooks = new ArrayList<>();

    }
}
