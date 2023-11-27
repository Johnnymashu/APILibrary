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


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        Iterable<Book> bookIts = bookRepository.findAll();
        bookIts.forEach(books::add);
        return books;
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> books = bookRepository.findById(id);
        return books.orElseGet(() -> new Book("Invalid Author", "Invalid Title", "Fiction"));
    }

    @Override
    public List<Book> findByTitleContains(String titleFilter) {
        return bookRepository.findByTitleContains(titleFilter);
    }

    @Override
    public List<Book> findByGenreContains(String genreFilter) {
        return bookRepository.findByGenreContains(genreFilter);
    }

    @Override
    public List<Book> findByAuthorContains(String authorFilter) {
        return bookRepository.findByAuthorContains(authorFilter);
    }

    @Override
    public Book save(Book b) {
        return bookRepository.save(b);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
