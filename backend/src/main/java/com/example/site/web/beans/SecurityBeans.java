package com.example.site.web.beans;

import com.example.site.repositories.repos.UserRepository;
import com.example.site.security.CustomUserDetails;
import com.example.site.security.Filter;
import com.example.site.security.JWTUtil;
import com.example.site.security.SecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityBeans {

//    @Bean
//    SecurityConfiguration SecurityConfiguration(CustomUserDetails customUserDetails, Filter filter) {
//        return new SecurityConfiguration(customUserDetails, filter);
//    }

    @Bean
    CustomUserDetails CustomUserDetails(UserRepository repository) {
        return new CustomUserDetails(repository);
    }

    @Bean
    JWTUtil JWTUtil() {
        return new JWTUtil();
    }

    @Bean
    Filter Filter(CustomUserDetails customUserDetails, JWTUtil jwtUtil){
        return new Filter(jwtUtil, customUserDetails);
    }


}
