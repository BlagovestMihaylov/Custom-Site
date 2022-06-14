package com.example.site.web.controllers;

import com.example.site.core.models.Post;
import com.example.site.core.models.User;
import com.example.site.core.services.UserService;
import com.example.site.web.models.UserInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UsersController {
    private final com.example.site.core.services.UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> listUsers(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return userService.listUsers(page, pageSize);
    }

    @PostMapping(value = "/create")
    public User createUser(@RequestBody UserInput userInput) {
        return userService.createUser(userInput.username, userInput.email, userInput.password, userInput.phone_number, userInput.image_url);
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "/{id}/posts")
    public List<Post> getUserPosts(@PathVariable("id") Integer id) {
        return userService.getUserPosts(id);
    }

    @PostMapping(value = "follow/{myId}&{otherId}")
    public void followSomebody(@PathVariable("myId") Integer myId,
                               @PathVariable("otherId") Integer otherId) {

        userService.followSomebody(myId, otherId);
    }

    @DeleteMapping(value = "unfollow/{myId}&{otherId}")
    public void unfollowSomebody(@PathVariable("myId") Integer myId,
                                 @PathVariable("otherId") Integer otherId) {
        userService.unfollowSomebody(myId, otherId);
    }


    @GetMapping("/{id}/followers")
    public List<User> getUserFollowers(@PathVariable("id") Integer id) {
        return userService.getFollowers(id);
    }

    @GetMapping("/{id}/followings")
    public List<User> getUserFollowings(@PathVariable("id") Integer id) {
        return userService.getFollowings(id);
    }
}
