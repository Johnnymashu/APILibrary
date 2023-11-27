package com.example.controller;

import com.example.model.Book;
import com.example.model.Movie;
import com.example.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        List<Movie> movies = movieService.findAll();
        return movies;
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
