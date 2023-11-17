package com.example.repository;

import com.example.model.Periodicals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodicalRepository extends CrudRepository<Periodicals, Integer> {
}
