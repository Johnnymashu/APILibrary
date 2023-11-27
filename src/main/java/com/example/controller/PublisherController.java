package com.example.controller;
import com.example.model.Publisher;
import com.example.service.PublisherService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/publishers")
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherService.save(publisher);
    }

    @PutMapping("/publishers")
    public Publisher updatePublisher(@RequestBody Publisher publisher){
        return publisherService.save(publisher);
    }

    @DeleteMapping("/delete/publishers/{id}")
    public String deletePublisher(@PathVariable Long id){
        publisherService.deleteById(id);
        return "record deleted!";
    }


}
