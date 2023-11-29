package com.example.dto;

import com.example.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;
    private String author;
    private String title;
    private String genre;

    Publisher releaser;



    private UserDto user;

    public BookDto(Long id, String author, String title, String genre, Publisher releaser){
        this.id = id;
        this.author=author;
        this.title=title;
        this.genre=genre;
        this.releaser = releaser;
    }
}
