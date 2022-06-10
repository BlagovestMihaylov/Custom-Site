package com.example.site.web.models;

public class UserInput {
    public final Integer id;
    public final String username;
    public final String email;
    public final String password;
    public final String phone_number;
    public final String registration_date;
    public final String image_url;

    public UserInput(Integer id, String username, String email, String password, String phone_number, String registration_date, String image_url) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.registration_date = registration_date;
        this.image_url = image_url;
    }
}
