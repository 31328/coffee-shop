package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.entity.UserEntity;
import com.hanghae.coffeeshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {
    private final UserService userService;
    private final TempConverter tempConverter;

    @Autowired
    public UserDetailsImpl(UserService userService, TempConverter tempConverter) {
        this.userService = userService;
        this.tempConverter = tempConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.getUserByEmail(username);
        return tempConverter.userDtoToEntity(userDto);
    }


}
