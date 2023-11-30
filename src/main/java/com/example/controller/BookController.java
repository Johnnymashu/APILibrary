package com.example.controller;

import com.example.dto.BookDto;
import com.example.model.Book;
import com.example.service.BookService;
import com.example.util.BookDtoConverter;
import io.micrometer.common.util.StringUtils;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class BookController {

    BookService bookService;


    public BookController(BookService bookService){
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public List<Book> findAll(
            @PathParam("titleFilter")String titleFilter,
            @PathParam("genreFilter")String genreFilter,
            @PathParam("authorFilter")String authorFilter

    ){
        List<Book> books;
        if(StringUtils.isNotBlank(titleFilter)){
            books = bookService.findByTitleContains(titleFilter);
        }
        else if(StringUtils.isNotBlank(genreFilter)){
            books = bookService.findByGenreContains(genreFilter);
        }
        else if(StringUtils.isNotBlank(authorFilter)){
            books = bookService.findByAuthorContains(authorFilter);
        }
        else {
            books = bookService.findAll();
        }
            return books;
        }

        @GetMapping("/books/{id}")
    public BookDto getBook(@PathVariable Long id){
        return BookDtoConverter.convert(bookService.findById(id));
          //  return bookService.findById(id);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        log.debug(book.getReleaser()+ "");
        return bookService.save(book);
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/delete/books/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteById(id);
        return "record deleted!";
    }

}
