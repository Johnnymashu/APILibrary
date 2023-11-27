package com.example.service;
import com.example.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();

    Movie findById(Long id);

    public Movie save(Movie m);

    public void deleteById(Long id);
}
