package com.example.service;

import com.example.model.Book;
import com.example.model.Movie;
import com.example.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class MovieServiceImpl implements MoviesService{

    MovieRepository movieRepository;


    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        Iterable<Movie> movieIterable = movieRepository.findAll();
        movieIterable.forEach(movies::add);
        return movies;
    }

    @Override
    public Movie findById(int id) {
        Optional<Movie> movies = movieRepository.findById(id);
        return movies.orElseGet(() -> new Movie( "actors", "director"));
    }
}
