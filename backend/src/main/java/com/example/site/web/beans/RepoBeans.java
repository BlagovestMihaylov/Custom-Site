package com.example.site.web.beans;

import com.example.site.repositories.mariaDB.MariaDBUserRepository;
import com.example.site.repositories.repos.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class RepoBeans {
    @Bean
    public UserRepository UserRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MariaDBUserRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
