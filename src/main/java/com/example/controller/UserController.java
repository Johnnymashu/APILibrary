package com.example.controller;
import com.example.dto.UserDto;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.UserDtoConverter;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public UserDto findById(@PathVariable Long id){
        return UserDtoConverter.convertWithoutBooks(userService.findById(id));
        //return userService.findById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User users) {
        return userService.save(users);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User users){
        return userService.save(users);
    }

    @DeleteMapping("/delete/users/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return "record deleted!";
    }
}
