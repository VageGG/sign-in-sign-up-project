package com.example.service;

import com.example.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public LoginService(AuthenticationManager authenticationManager,
                        JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public String login(User user) throws NoSuchAlgorithmException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return this.jwtService.generateToken(user.getName());
        }
        throw new RuntimeException("bad credentials");
    }
}
