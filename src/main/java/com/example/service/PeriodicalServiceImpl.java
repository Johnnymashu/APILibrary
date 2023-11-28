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
    public List<Periodicals> findByTitleContains(String titleFilter) {
        return periodicalRepository.findByTitleContains(titleFilter);
    }

    @Override
    public List<Periodicals> findByGenreContains(String genreFilter) {
        return periodicalRepository.findByGenreContains(genreFilter);
    }

    @Override
    public List<Periodicals> findByAuthorContains(String authorFilter) {
        return periodicalRepository.findByAuthorContains(authorFilter);
    }

    @Override
    public Periodicals findById(Long id) {
        Optional<Periodicals> periodicals = periodicalRepository.findById(id);
        return periodicals.orElseGet(Periodicals::new);
    }

    @Override
    public Periodicals save(Periodicals p) {
        return periodicalRepository.save(p);
    }

    @Override
    public void deleteById(Long id) {
        periodicalRepository.deleteById(id);
    }
}
