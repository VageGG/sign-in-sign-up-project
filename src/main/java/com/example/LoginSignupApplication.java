package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginSignupApplication {
    public static void main(String[] args) {
        // This will launch the Spring Boot application
        // The main method will start the server and listen for incoming requests
        SpringApplication.run(LoginSignupApplication.class, args);
    }
}
