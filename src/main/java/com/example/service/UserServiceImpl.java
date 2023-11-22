package com.example.service;

import com.example.model.Book;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Iterable<User> userIts = userRepository.findAll();
        userIts.forEach(users::add);
        return users;
    }

    @Override
    public User findById(int id) {
        Optional<User> users = userRepository.findById(id);
        return users.orElseGet(() -> new User(0, "Johnnymashu@gmail.com"));
    }
}
