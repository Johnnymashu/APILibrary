package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name="t_user")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;
}
