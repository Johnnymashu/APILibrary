package com.example.repository;


import com.example.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByTitleContains(String titleFilter);
    List<Movie> findByGenreContains(String genreFilter);
    List<Movie> findByAuthorContains(String authorFilter);

}
