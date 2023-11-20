package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import io.micrometer.common.util.StringUtils;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class BookController {

    BookService bookService;


    public BookController(BookService bookService){
        this.bookService = bookService;
    }
//@GetMapping("/books")
//    public List<Book> findAll(){
//       List<Book> books = bookService.findAll();
//        return books;
//}

    @GetMapping("/books")
    public List<Book> findAll(@PathParam("filter")String filter){
        List<Book> books = Collections.emptyList();
        if(StringUtils.isNotBlank(filter)){
            books = bookService.findByTitleContains(filter);
        }
        else {
            books = bookService.findAll();
        }
            return books;
        }

        @GetMapping("/books/{id}")
    public Book getBook(@PathVariable int id){
        return bookService.findById(id);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/delete/books/{id}")
    public String deleteBook(@PathVariable int id){
        bookService.deleteById(id);
        return "record deleted!";
    }

}
