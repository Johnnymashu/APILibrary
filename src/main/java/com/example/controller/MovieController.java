package com.example.controller;


import com.example.model.Movie;
import com.example.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    MovieService movieService;


    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movie findById(@PathVariable Long id){
        return movieService.findById(id);
    }

    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("/movies")
    public Movie updateMovie(@RequestBody Movie movie){
        return movieService.save(movie);
    }

    @DeleteMapping("/delete/movies/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieService.deleteById(id);
        return "record deleted!";
    }
}
