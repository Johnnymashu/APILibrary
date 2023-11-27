package com.example.controller;

import com.example.model.Publisher;
import com.example.service.PublisherService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor

@Data
public class PublisherController {

     PublisherService publisherService;

    @GetMapping("/publishers")
    public List<Publisher> findAll(){
        return publisherService.findAll();
    }

    @GetMapping("/publishers/{id}")
    public Publisher findById(@PathVariable Long id){
        return publisherService.findById(id);
    }


}
