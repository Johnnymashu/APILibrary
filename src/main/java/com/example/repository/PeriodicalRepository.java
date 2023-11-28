package com.example.repository;

import com.example.model.Movie;
import com.example.model.Periodicals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodicalRepository extends CrudRepository<Periodicals, Long> {
    List<Periodicals> findByTitleContains(String titleFilter);
    List<Periodicals> findByGenreContains(String genreFilter);
    List<Periodicals> findByAuthorContains(String authorFilter);
}
