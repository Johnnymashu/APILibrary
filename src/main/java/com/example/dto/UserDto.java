package com.example.dto;

import com.example.model.Book;
import com.example.model.DebitDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String email;

    private DebitDetails debitDetails;

    @JsonIgnore
    private List<Book> books;

    public UserDto(){

    }


    public UserDto(Long id, String email, DebitDetails debitDetails){
        this.id = id;
        this.email = email;
        this.debitDetails = debitDetails;
    }

}
