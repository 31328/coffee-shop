package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.services.UserService;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @PatchMapping("/add-point/{userId}")
    public ResponseEntity<String> addPoint(@PathVariable Long userId, @PathVariable("points") Integer points) {
        userService.addPoints(userId, points);
        return new ResponseEntity<>(points + "points have been added to user with id:" + userId, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable ("id") Long userId ){
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    //delete
    //update user, get - set points
    // set password
    //

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable ("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<String> ("User with id: " + userId + " has been removed", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> updateUser(@PathVariable ("id") Long userId, Errors errors){
        if (errors.hasErrors()){
            throw new RuntimeException("User data has not being validated");
        }
        userService.updateUser(, userId);
        return new ResponseEntity<String>("Userwith id: " + userId + "updated", HttpStatus.OK);
    }
}
