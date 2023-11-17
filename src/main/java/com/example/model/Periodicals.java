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
public class Periodicals {

    @Id
    @GeneratedValue
    private Integer id;
    private String date; //later on make this actual DATETIME
    private String title;
    private String author;
    private String genre;
}
