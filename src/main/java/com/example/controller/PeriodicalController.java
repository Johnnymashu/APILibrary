package com.example.controller;
import com.example.model.Periodicals;
import com.example.service.PeriodicalService;
import io.micrometer.common.util.StringUtils;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeriodicalController {


    PeriodicalService periodicalService;

    public PeriodicalController(PeriodicalService periodicalService){
        this.periodicalService = periodicalService;
    }

    @GetMapping("/periodicals")
    public List<Periodicals> findAll(
            @PathParam("titleFilter")String titleFilter,
            @PathParam("genreFilter")String genreFilter,
            @PathParam("authorFilter")String authorFilter

    ){
        List<Periodicals> periodicals;
        if(StringUtils.isNotBlank(titleFilter)){
            periodicals= periodicalService.findByTitleContains(titleFilter);
        }
        else if(StringUtils.isNotBlank(genreFilter)){
            periodicals = periodicalService.findByGenreContains(genreFilter);
        }
        else if(StringUtils.isNotBlank(authorFilter)){
            periodicals = periodicalService.findByAuthorContains(authorFilter);
        }
        else {
            periodicals = periodicalService.findAll();
        }
        return periodicals;
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
