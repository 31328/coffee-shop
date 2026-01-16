package com.hanghae.coffeeshop.controllers;


import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.exceptions.DataNotValidatedException;
import com.hanghae.coffeeshop.services.CustomerService;
import com.hanghae.coffeeshop.services.UserService;
import com.hanghae.coffeeshop.utils.AuthenticationRequest;
import com.hanghae.coffeeshop.utils.JwtUtil;
import com.hanghae.coffeeshop.utils.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.Optional;

@RestController
public class MainController {
    private final CustomerService customerService;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final TempConverter tempConverter;

    @Autowired
    public MainController(CustomerService customerService, UserService userService, JwtUtil jwtUtil, TempConverter tempConverter) {
        this.customerService = customerService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.tempConverter = tempConverter;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registration(@RequestBody @Validated RegistrationForm form, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("Form data has not being validated");
        }
        customerService.addCustomer(form);
        return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody AuthenticationRequest request) throws CredentialNotFoundException {
        Optional<Authentication> authenticationOptional = userService.authenticateUser(request.getUsername(), request.getPassword());
        if (authenticationOptional.isEmpty()) {
            throw new CredentialNotFoundException("Invalid User Name or Password");
        } UserDto userDto = userService.getUserByEmail(request.getUsername());
        String jwtToken = jwtUtil.generateToken(tempConverter.userDtoToEntity(userDto));
        userDto.setAuthToken(jwtToken);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


}
