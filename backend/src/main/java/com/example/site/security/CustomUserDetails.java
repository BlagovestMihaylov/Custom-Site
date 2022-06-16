package com.example.site.security;

import com.example.site.core.models.User;
import com.example.site.core.services.Mapper;
import com.example.site.repositories.repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class CustomUserDetails implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetails(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Mapper.fromUserDAO(repository.getUserByUsername(username));
        return new org.springframework.security.core.userdetails.User(user.username, user.password, new ArrayList<>());
    }
}
