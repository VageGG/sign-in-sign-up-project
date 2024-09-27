package com.example.controller;

import com.example.converter.UserConverter;
import com.example.entity.User;
import com.example.model.UserDto;
import com.example.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class LoginController {


    private final LoginService loginService;

    private final UserConverter userConverter;

    public LoginController(LoginService loginService, UserConverter userConverter) {
        this.loginService = loginService;
        this.userConverter = userConverter;
    }

    private String token;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        User user = this.userConverter.convertToEntity(userDto, new User());
        token = this.loginService.login(user);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/")
    public String greet() {
        return "welcome";
    }
}
