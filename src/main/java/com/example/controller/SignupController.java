package com.example.controller;

import com.example.converter.UserConverter;
import com.example.entity.User;
import com.example.model.UserDto;
import com.example.service.SignupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class SignupController {


    private final SignupService signupService;

    private final UserConverter userConverter;

    public SignupController(SignupService signupService, UserConverter userConverter) {
        this.signupService = signupService;
        this.userConverter = userConverter;
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> signup(@RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        signupService.signup(this.userConverter.convertToEntity(userDto, new User()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
