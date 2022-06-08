package com.example.site.repositories.repos;

import com.example.site.repositories.models.UserDAO;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository {
    UserDAO createUser(
            String username, String email,
            String password, String phone_number);

    UserDAO getUserById(Integer id);

    List<UserDAO> listUsers(int page, int pageSize);

    void deleteUser(Integer id);
}
