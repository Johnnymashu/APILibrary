package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Publisher {
    @Id
    @GeneratedValue
    private Integer id;
    private String brand;
    private int prestige;


    public Publisher(String brand, int prestige){
        this.brand = brand;
        this.prestige = prestige;

    }
}
