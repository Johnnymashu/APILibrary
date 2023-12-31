package com.example.repository;

import com.example.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

List<Book> findByTitleContains(String titleFilter);
List<Book> findByGenreContains(String genreFilter);
List<Book> findByAuthorContains(String authorFilter);
}
