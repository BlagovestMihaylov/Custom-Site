package com.example.site.web.controllers;


import com.example.site.core.models.User;
import com.example.site.core.services.UserService;
import com.example.site.web.models.UserInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {
    private final com.example.site.core.services.UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User createUser(@RequestBody UserInput userInput) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userService.createUser(userInput.username, userInput.email, userInput.password, userInput.phone_number, userInput.image_url);
    }
}
