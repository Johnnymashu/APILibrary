package com.example.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Entity
@Table(name="t_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
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



@JsonManagedReference(value = "user_books")
    @OneToMany
    @JoinTable
            (name = "USER_BOOKS",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
    inverseJoinColumns= { @JoinColumn(name="book_id", referencedColumnName = "book_id")})
    private List<Book> borrowedBooks;

    public User(String email, DebitDetails debitDetails) {
        this.email = email;
        this.debitDetails = debitDetails;
        borrowedBooks = new ArrayList<>();

    }

}
