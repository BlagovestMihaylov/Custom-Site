package com.example.site.web.controllers;

import com.example.site.core.models.User;
import com.example.site.core.services.UserService;
import com.example.site.repositories.models.UserDAO;
import com.example.site.repositories.repos.UserRepository;
import com.example.site.web.models.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsersControllerTest {

    private UserRepository userRepository;
    private UsersController usersController;

    @BeforeEach
    public void setUp(){
        userRepository = mock(UserRepository.class);
        usersController = new UsersController(new UserService(userRepository));
    }
    @Test
    void itShouldListUsers() {
        UserDAO userDAO = new UserDAO(1, "Petko", "petko@hello.com", "hello123", "222287218271", "2022-06-10", "https://secret.bg/wp-content/uploads/2021/09/237139716_565715657941481_5556308605494149034_n.jpg");
        List<UserDAO> users = new ArrayList<>();
        users.add(userDAO);
        when(userRepository.listUsers(anyInt(), anyInt())).thenReturn(users);


        List<User> response = usersController.listUsers(0, 1);


        assertEquals(users.size(), response.size());
        for (int i = 0; i < users.size(); i++) {
            assertEquals(users.get(i).id, response.get(i).id);
        }
    }

    @Test
    void createUser() {
    }

//    @Test
//    @Disabled
//    void getUser() throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//        UserDAO lidka = new UserDAO(6, "Lidka", "stamatova@hello.com", "$2a$10$8jKfTN3kfOopThuv6FAFZejnislSvnDUT.X.cNH6BJ6ViLA3hFH.2", "1234567890", "2022-06-10", "https://yt3.ggpht.com/ytc/AKedOLQIo9gPNcTYb1p0bC5EOG5FCyc7A5Sj4UQhXhk2Yg=s900-c-k-c0x00ffffff-no-rj");
//        User lidia = new User(6,lidka.username, lidka.email, lidka.password, lidka.phone_number, lidka.image_url, "2022-06-10");
//        when(userRepository.getUserById(anyInt() )).thenReturn(lidka).
//        assertEquals(lidia, usersController.getUser(6));
//    }

    @Test
    void getUsers(){


    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserPosts() {

    }

    @Test
    void followSomebody() {

    }

    @Test
    void unfollowSomebody() {
    }

    @Test
    void getUserFollowers() {
        UserDAO lidka = new UserDAO(6, "Lidka", "stamatova@hello.com", "$2a$10$8jKfTN3kfOopThuv6FAFZejnislSvnDUT.X.cNH6BJ6ViLA3hFH.2", "1234567890", "2022-06-10", "https://yt3.ggpht.com/ytc/AKedOLQIo9gPNcTYb1p0bC5EOG5FCyc7A5Sj4UQhXhk2Yg=s900-c-k-c0x00ffffff-no-rj");
      List<UserDAO> users = new ArrayList<>();
      users.add(lidka);
      when(userRepository.getFollowers(anyInt())).thenReturn(users);
        List<User> response = usersController.getUserFollowers(5);


        assertEquals(users.size(), response.size());
        for (int i = 0; i < users.size(); i++) {
            assertEquals(users.get(i).id, response.get(i).id);
        }
    }

    @Test
    void getUserFollowings() {
        UserDAO lidka = new UserDAO(6, "Lidka", "stamatova@hello.com", "$2a$10$8jKfTN3kfOopThuv6FAFZejnislSvnDUT.X.cNH6BJ6ViLA3hFH.2", "1234567890", "2022-06-10", "https://yt3.ggpht.com/ytc/AKedOLQIo9gPNcTYb1p0bC5EOG5FCyc7A5Sj4UQhXhk2Yg=s900-c-k-c0x00ffffff-no-rj");
        List<UserDAO> users = new ArrayList<>();
        users.add(lidka);
        when(userRepository.getFollowings(anyInt())).thenReturn(users);
        List<User> response = usersController.getUserFollowings(5);


        assertEquals(users.size(), response.size());
        for (int i = 0; i < users.size(); i++) {
            assertEquals(users.get(i).id, response.get(i).id);
        }
    }
}