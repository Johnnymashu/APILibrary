package com.example.util;

import com.example.dto.BookDto;
import com.example.dto.UserDto;
import com.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDtoConverter {

    public static UserDto convert(User user){
        return new UserDto(user.getId(), user.getEmail(), user.getDebitDetails(), BookDtoConverter.convertAll(user.getBorrowedBooks()));
    }

    public static List<UserDto> convertAll (List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userList){
            userDtoList.add(convertWithoutBooks(user));
        }
        return userDtoList;
    }


    public static UserDto convertWithoutBooks(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getDebitDetails());
    }
}