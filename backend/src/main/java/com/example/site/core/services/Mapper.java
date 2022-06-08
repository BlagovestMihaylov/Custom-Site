package com.example.site.core.services;

import com.example.site.core.models.User;
import com.example.site.repositories.models.UserDAO;

public class Mapper {
    public static User fromUserDAO(UserDAO userDAO) {
        return new User(userDAO.id,
                userDAO.username,
                userDAO.email,
                userDAO.password,
                userDAO.phone_number,
                userDAO.registration_date);
    }
}
