package com.example.controller;
import com.example.model.Periodicals;
import com.example.service.PeriodicalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeriodicalController {


    PeriodicalService periodicalService;

    public PeriodicalController(PeriodicalService periodicalService){
        this.periodicalService = periodicalService;
    }

    @GetMapping("/periodicals")
    public List<Periodicals> findAll(){
        return periodicalService.findAll();
    }

    @GetMapping("/periodicals/{id}")
    public Periodicals findById(@PathVariable Long id){
        return periodicalService.findById(id);
    }

    @PostMapping("/periodicals")
    public Periodicals createPeriodicals(@RequestBody Periodicals periodicals) {
        return periodicalService.save(periodicals);
    }

    @PutMapping("/periodicals")
    public Periodicals updatePeriodicals(@RequestBody Periodicals periodicals){
        return periodicalService.save(periodicals);
    }

    @DeleteMapping("/delete/periodicals/{id}")
    public String deletePeriodicals(@PathVariable Long id){
        periodicalService.deleteById(id);
        return "record deleted!";
    }

}
