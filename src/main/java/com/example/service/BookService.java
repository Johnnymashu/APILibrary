package com.example.service;

import com.example.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    List<Book> findByTitleContains(String filter);

    public Book save(Book b);

    public void deleteById(Long id);
}
