package com.example.util;

import com.example.dto.UserDto;
import com.example.model.User;

public class UserDtoConverter {

    public static UserDto convert(User user){
        return new UserDto(user.getId(), user.getEmail(), user.getDebitDetails(), user.getBorrowedBooks());
    }


    public static UserDto convertWithoutBooks(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getDebitDetails());
    }
}