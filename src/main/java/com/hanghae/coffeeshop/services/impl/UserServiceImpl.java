package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.repositories.UserRepository;
import com.hanghae.coffeeshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TempConverter tempConverter;

    @Override
    public UserDto getUser(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void addUser(UserDto userDto) {

    }

    @Override
    public void updateUser(UserDto userDto) {

    }

    @Override
    public void addPoints(Long userId, Long pointId) {

    }

    @Override
    public void deletePoints(Long userId, Long pointId) {

    }
}
