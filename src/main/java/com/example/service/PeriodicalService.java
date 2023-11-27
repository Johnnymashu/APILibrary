package com.example.service;


import com.example.model.Periodicals;

import java.util.List;

public interface PeriodicalService {

    List<Periodicals> findAll();

    Periodicals findById(Long id);

    public Periodicals save(Periodicals p);

    public void deleteById(Long id);
}
