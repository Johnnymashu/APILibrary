package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Entity
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class Periodicals {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "periodicals_seq")
    @SequenceGenerator(
            name = "periodicals_seq",
            sequenceName = "periodicals_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;
    private String dates;
   // private  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
    private String title;
    private String author;
    private String genre;
}
