package com.example.site.web.beans;

import com.example.site.repositories.mariaDB.*;
import com.example.site.repositories.repos.*;
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
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MariaDBPostRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    public FollowshipRepository FriendshipRepository(TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MariaDBFollowshipRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    public CommentRepository CommentRepository(TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MariaDBCommentRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    public ReportRepository ReportRepository(TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MariaDBReportRepository(txTemplate, jdbcTemplate);
    }
    @Bean
    public LikeRepository LikeRepository(TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate){
        return new MariaDBLikeRepository(txTemplate, jdbcTemplate);
    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
