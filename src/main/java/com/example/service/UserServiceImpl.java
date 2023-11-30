package com.example.service;

import com.example.model.DebitDetails;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

   DebitDetails debitDetails;

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
    public User findById(Long id) {
        Optional<User> users = userRepository.findById(id);
        return users.orElseGet(() -> new User("Johnnymashu@gmail.com",
               debitDetails
        ));
    }

    @Override
    public User save(User u) {
        return userRepository.save(u);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
