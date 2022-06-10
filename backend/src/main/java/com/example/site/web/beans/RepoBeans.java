package com.example.site.web.beans;

import com.example.site.repositories.mariaDB.MariaDBFriendshipRepository;
import com.example.site.repositories.mariaDB.MariaDBPostRepository;
import com.example.site.repositories.mariaDB.MariaDBUserRepository;
import com.example.site.repositories.repos.FriendshipRepository;
import com.example.site.repositories.repos.PostRepository;
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
    public PostRepository PostRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate){
        return new MariaDBPostRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    public FriendshipRepository FriendshipRepository(TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate){
        return  new MariaDBFriendshipRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
