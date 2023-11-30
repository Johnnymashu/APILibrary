package com.example.util;

import com.example.dto.BookDto;
import com.example.model.Book;

public class BookDtoConverter {

    public static BookDto convert (Book book){
        return new BookDto(book.getId(), book.getAuthor(), book.getTitle(), book.getGenre(), book.getReleaser(), UserDtoConverter.convertWithoutBooks(book.getBorrower()));


    }
    public static BookDto convertWithoutUser(Book book){
        return new BookDto(book.getId(), book.getAuthor(), book.getTitle(), book.getGenre(), book.getReleaser());
    }
}
