package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

//@Entity
@MappedSuperclass
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class DigitalMaterials {

//    @Id
//    @GeneratedValue
//    private Integer id;
    private String author;
    private String genre;
    private String title;
}
