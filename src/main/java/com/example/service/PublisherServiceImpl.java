package com.example.service;


import com.example.model.Book;
import com.example.model.Publisher;
import com.example.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService{

     PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findAll() {
        List<Publisher> publishers = new ArrayList<>();
        Iterable<Publisher> publisherits = publisherRepository.findAll();
        publisherits.forEach(publishers::add);
        return publishers;
    }

    @Override
    public Publisher findById(Long id) {
        Optional<Publisher> publishers = publisherRepository.findById(id);
        return publishers.orElseGet(() -> new Publisher("Go Away", 000));
    }

    @Override
    public Publisher save(Publisher p) {
//        Optional<Publisher> publisher = publisherRepository.findById(p.getReleaser().getId());
//        b.setReleaser(publisher.get());
        return publisherRepository.save(p);
    }

    @Override
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
}
