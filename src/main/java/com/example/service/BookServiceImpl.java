package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public List<Book> findByTitleContains(String filter) {
        return bookRepository.findByTitleContains(filter);
    }
}
