package com.example.controller;

import com.example.model.Movie;
import com.example.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    MovieService movieService;


    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> findAll(){
        List<Movie> movies = movieService.findAll();
        return movies;
    }

    @GetMapping("/movies/{id}")
    public Movie findById(@PathVariable int id){
        return movieService.findById(id);
    }
}
