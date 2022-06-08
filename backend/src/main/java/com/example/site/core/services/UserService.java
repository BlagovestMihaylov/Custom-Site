package com.example.site.core.services;

import com.example.site.core.models.User;
import com.example.site.repositories.repos.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {


    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String email, String password, String phone_number) {
        return Mapper.fromUserDAO(userRepository.createUser(username, email, password, phone_number));
    }

    public User getUserById(int id) {
        return Mapper.fromUserDAO(userRepository.getUserById(id));
    }

    public List<User> listUsers(int page, int pageSize) {
        return userRepository.listUsers(page, pageSize)
                .stream()
                .map(Mapper::fromUserDAO)
                .collect(Collectors.toList());
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

}
