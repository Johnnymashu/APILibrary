package com.example.service;


import com.example.model.User;

import java.util.List;

public interface UserService {


    List<User> findAll();


    User findById(Long id);

    public User save(User u);

    public void deleteById(Long id);
}
