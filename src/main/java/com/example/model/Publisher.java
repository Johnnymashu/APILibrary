package com.example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @GeneratedValue
    private Long id;
    private String brand;
    private int prestige;

    @JsonManagedReference
    @OneToMany(mappedBy = "releaser")
    private List<Book> releasedBooks;


    public Publisher(String brand, int prestige){
        this.brand = brand;
        this.prestige = prestige;
        releasedBooks = new ArrayList<>();

    }
}
