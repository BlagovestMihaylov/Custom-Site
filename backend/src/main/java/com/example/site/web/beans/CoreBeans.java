package com.example.site.web.beans;

import com.example.site.core.services.UserService;
import com.example.site.repositories.repos.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CoreBeans {

    @Bean
    public UserService UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        return new UserService(repository, passwordEncoder);
    }

}
