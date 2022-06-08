package com.example.site.web.controllers;

import com.example.site.core.models.User;
import com.example.site.core.services.UserService;
import com.example.site.web.models.UserInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/Users")
public class UsersController {
    private com.example.site.core.services.UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> listUsers(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return userService.listUsers(page, pageSize);
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/create")
    public User createUser(@RequestBody UserInput userInput) {
        return userService.createUser(userInput.username, userInput.email, userInput.password, userInput.phone_number);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
