package com.example.site.core.models;

import java.time.LocalDate;
import java.util.Date;

public class User {
    public final Integer id;
    public final String username;
    public final String email;
    public final String password;
    public final String phone_number;

    public final String registration_date;

    public User(Integer id, String username, String email, String password, String phone_number, String registration_date) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.registration_date = registration_date;
    }
}
