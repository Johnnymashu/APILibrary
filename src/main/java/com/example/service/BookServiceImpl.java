package com.example.service;

import com.example.model.Book;
import com.example.model.Publisher;
import com.example.repository.BookRepository;
import com.example.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository){
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        Iterable<Book> bookIts = bookRepository.findAll();
        bookIts.forEach(books::add);
        return books;
    }

    @Override
    public Book findById(int id) {
        Optional<Book> books = bookRepository.findById(id);
        return books.orElseGet(() -> new Book(0, "Invalid Author", "Invalid Title", "Fiction"));
    }

    @Override
    public List<Book> findByTitleContains(String filter) {
        return bookRepository.findByTitleContains(filter);
    }

    @Override
    public Book save(Book b) {
        Optional<Publisher> publisher = publisherRepository.findById(b.getReleaser().getId());
        b.setReleaser(publisher.get());
        return bookRepository.save(b);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
