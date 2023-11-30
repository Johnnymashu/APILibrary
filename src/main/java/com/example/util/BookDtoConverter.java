package com.example.util;

import com.example.dto.BookDto;
import com.example.dto.UserDto;
import com.example.model.Book;
import com.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class BookDtoConverter {

    public static BookDto convert (Book book){
        return new BookDto(book.getId(), book.getAuthor(), book.getTitle(), book.getGenre(), UserDtoConverter.convertAll(book.getUsers()));




    }
    public static List<BookDto> convertAll (List<Book> bookList) {
        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book book : bookList){
            bookDtoList.add(convertWithoutUser(book));
        }
        return bookDtoList;
    }

    public static BookDto convertWithoutUser(Book book){
        return new BookDto(book.getId(), book.getAuthor(), book.getTitle(), book.getGenre());
    }
}
