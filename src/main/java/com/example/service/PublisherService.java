package com.example.service;



import com.example.model.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> findAll();

    Publisher findById(Long id);

    Publisher save(Publisher p);

    void deleteById(Long id);
}
