package com.example.service;

import com.example.model.Movie;

import com.example.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;


    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        Iterable<Movie> movieIterable = movieRepository.findAll();
        movieIterable.forEach(movies::add);
        return movies;
    }

    @Override
    public Movie findById(Long id) {
        Optional<Movie> movies = movieRepository.findById(id);
        return movies.orElseGet(() -> new Movie( "actors", "director"));
    }

    @Override
    public Movie save(Movie m) {
        return movieRepository.save(m);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
