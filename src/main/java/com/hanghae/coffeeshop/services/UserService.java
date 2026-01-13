package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.dto.UserDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService {


    UserDto getUser(Long userId);

    UserDto getCurrentUser();

    UserDto getUserByEmail(String email);

    void deleteUser(Long userId);

    void banUser(Long userId);

    void unbanUser(Long userId);

    UserDto addUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    List<UserDto> listAllUsers();

    Optional<Authentication> authenticateUser(String userName, String password);
}
