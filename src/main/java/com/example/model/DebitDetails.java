package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DebitDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debit_seq")
    @SequenceGenerator(
            name = "debit_seq",
            sequenceName = "debit_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private String cardHolder;

    private int sortCode;

    private int accountNumber;


}
