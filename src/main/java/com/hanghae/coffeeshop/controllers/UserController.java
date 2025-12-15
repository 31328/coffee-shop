package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @PatchMapping("/add-point/{userId}")
    public void addPoint(@PathVariable String userId, @RequestBody Map<String, Integer> requestBody) {
        userService.addPoint(userId, requestBody.get("point"));
    }
}
