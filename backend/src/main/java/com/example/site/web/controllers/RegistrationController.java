package com.example.site.web.controllers;


import com.example.site.core.models.Post;
import com.example.site.core.models.User;
import com.example.site.core.services.PostService;
import com.example.site.core.services.UserService;
import com.example.site.web.models.UserInput;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {
    private final com.example.site.core.services.UserService userService;
    private final PostService postService;

    public RegistrationController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping()
    public User createUser(@RequestBody UserInput userInput) throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("Test");
        return userService.createUser(userInput.username, userInput.email, userInput.password, userInput.phone_number, userInput.image_url);
    }
    @GetMapping(value = "/posts") 
    public List<Post> getPosts(){
        return postService.getPosts();
    }
}
