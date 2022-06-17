package com.example.site.web.controllers;


import com.example.site.core.models.AuthRequest;
import com.example.site.security.Hashing;
import com.example.site.security.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final JWTUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public LoginController(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping(value = "/")
    public String welcome() {
        return "Hello";
    }

    @GetMapping(value = "/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
//        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username, Hashing.generateStoringPasswordHash(authRequest.password)));
                //        );
//        } catch (Exception e) {
//            throw new Exception("invalid username/password");
//        }
        return jwtUtil.generateToken(authRequest.username);
    }

}
