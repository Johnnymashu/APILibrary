package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/users")
    public List<User> findUsers(){
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id){
        return userService.findById(id);
    }
}
