package com.example.controller;
import com.example.model.Movie;
import com.example.service.MovieService;
import io.micrometer.common.util.StringUtils;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    MovieService movieService;


    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> findAll(
            @PathParam("titleFilter")String titleFilter,
            @PathParam("genreFilter")String genreFilter,
            @PathParam("authorFilter")String authorFilter

    ){
        List<Movie> movie;
        if(StringUtils.isNotBlank(titleFilter)){
            movie = movieService.findByTitleContains(titleFilter);
        }
        else if(StringUtils.isNotBlank(genreFilter)){
            movie = movieService.findByGenreContains(genreFilter);
        }
        else if(StringUtils.isNotBlank(authorFilter)){
            movie = movieService.findByAuthorContains(authorFilter);
        }
        else {
            movie = movieService.findAll();
        }
        return movie;
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
