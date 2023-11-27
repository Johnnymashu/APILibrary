package com.example.service;


import com.example.model.Periodicals;
import com.example.repository.PeriodicalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodicalServiceImpl implements  PeriodicalService{

    PeriodicalRepository periodicalRepository;

    public PeriodicalServiceImpl(PeriodicalRepository periodicalRepository){
        this.periodicalRepository = periodicalRepository;
    }

    @Override
    public List<Periodicals> findAll() {
        List<Periodicals> periodicalsList = new ArrayList<>();
        Iterable<Periodicals> periodicalsIterable = periodicalRepository.findAll();
        periodicalsIterable.forEach(periodicalsList::add);
        return periodicalsList;
    }

    @Override
    public Periodicals findById(Long id) {
        Optional<Periodicals> periodicals = periodicalRepository.findById(id);
        return periodicals.orElseGet(() -> new Periodicals());
    }
}
