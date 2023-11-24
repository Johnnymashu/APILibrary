package com.example.model;

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
public class Movie extends DigitalMaterials{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(
            name = "movie_seq",
            sequenceName = "movie_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;
    private String actors;
    private String director;

    public Movie(Integer id, String actors, String director) {
        this.actors = actors;
        this.director = director;
    }
}

