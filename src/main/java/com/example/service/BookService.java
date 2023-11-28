package com.example.service;

import com.example.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    List<Book> findByTitleContains(String titleFilter);

    List<Book> findByGenreContains(String genreFilter);

    List<Book> findByAuthorContains(String authorFilter);

    public Book save(Book b);

    public void deleteById(Long id);
}
