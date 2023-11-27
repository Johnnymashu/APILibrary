package com.example.controller;

import com.example.model.Periodicals;
import com.example.service.PeriodicalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeriodicalController {


    PeriodicalService periodicalService;

    public PeriodicalController(PeriodicalService periodicalService){
        this.periodicalService = periodicalService;
    }

    @GetMapping("/periodicals")
    public List<Periodicals> findAll(){
        List<Periodicals> periodicalsList = periodicalService.findAll();
        return periodicalsList;
    }

    @GetMapping("/periodicals/{id}")
    public Periodicals findById(@PathVariable Long id){
        return periodicalService.findById(id);
    }

}
