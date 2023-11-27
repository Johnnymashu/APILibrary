package com.example.model;
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


    private String author;
    private String genre;
    private String title;
}
