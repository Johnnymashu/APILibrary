package com.example.service;


import com.example.model.Movie;
import com.example.model.Periodicals;

import java.util.List;

public interface PeriodicalService {

    List<Periodicals> findAll();

    List<Periodicals> findByTitleContains(String titleFilter);

    List<Periodicals> findByGenreContains(String genreFilter);

    List<Periodicals> findByAuthorContains(String authorFilter);
    Periodicals findById(Long id);

    public Periodicals save(Periodicals p);

    public void deleteById(Long id);
}
