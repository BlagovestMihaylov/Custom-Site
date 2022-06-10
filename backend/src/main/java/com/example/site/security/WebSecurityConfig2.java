package com.example.site.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface WebSecurityConfig2 {
    void registerAuthentication(AuthenticationManagerBuilder authManagerBuilder) throws Exception;
}
//