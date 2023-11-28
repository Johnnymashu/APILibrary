package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="t_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_user_seq")
    @SequenceGenerator(
            name = "t_user_seq",
            sequenceName = "t_user_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private String email;
    @OneToOne(cascade = CascadeType.PERSIST)
    private DebitDetails debitDetails;

    @JsonBackReference
    @ManyToOne
    Book borrowed;

    @JsonManagedReference
    @OneToMany(mappedBy = "borrower")
    private List<Book> borrowedBooks;

    public User(String email, DebitDetails debitDetails) {
        this.email = email;
        this.debitDetails = debitDetails;
        borrowedBooks = new ArrayList<>();

    }

}
