package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.dto.UserDto;

public interface UserService {
    UserDto getUser(Long userId);

    void deleteUser(Long userId);

    void addUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void addPoints(Long userId, Long pointId);

    void deletePoints(Long userId, Long pointId);


}
