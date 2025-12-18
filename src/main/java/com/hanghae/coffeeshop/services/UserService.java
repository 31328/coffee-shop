package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.dto.UserDto;

import java.util.List;

public interface UserService {


    UserDto getUser(Long userId);

    void deleteUser(Long userId);

    UserDto addUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    void addPoints(Long userId, Integer points);

    void reducePoints(Long userId, Integer points);

    List<UserDto> listAllUsers();
}
