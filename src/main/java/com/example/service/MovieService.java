package com.example.service;

import com.example.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();

    Movie findById(Long id);

    List<Movie> findByTitleContains(String titleFilter);

    List<Movie> findByGenreContains(String genreFilter);

    List<Movie> findByAuthorContains(String authorFilter);

    public Movie save(Movie m);

    public void deleteById(Long id);
}
