package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.exceptions.DataNotValidatedException;
import com.hanghae.coffeeshop.services.UserService;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDto> findUser(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDto> addUser(@RequestBody @Validated UserDto userDto, Errors error) {
        if (error.hasErrors()) {
            throw new DataNotValidatedException("User data has not been validated");
        }
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User with id: " + userId + " has been removed", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long userId, @RequestBody @Validated UserDto userDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("User data failed validation");
        }
        userService.updateUser(userDto, userId);
        return new ResponseEntity<>("User with id: " + userId + "updated", HttpStatus.OK);
    }
}
