package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SignupService {
    private final UserRepo userRepo;

    public SignupService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public User signup(User user) throws NoSuchAlgorithmException {
        if (this.userRepo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("email has already been used");
        }

        if (!isEmailValid(user.getEmail())) {
            throw new RuntimeException("email is invalid");
        }

        if (this.userRepo.findByName(user.getName()) != null) {
            throw new RuntimeException("name is already in use");
        }

        if (!isPasswordValid(user.getPassword())) {
            throw new RuntimeException("password is invalid");
        }

        if (user.getName() == null) {
            throw new RuntimeException("name must be specified");
        }
        String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(pw_hash);

        return this.userRepo.save(user);
    }

    private boolean isEmailValid(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters long");
        }

        Pattern numberPattern = Pattern.compile("[0-9]");
        Pattern uppercasePattern = Pattern.compile("[A-Z]");
        Pattern lowercasePattern = Pattern.compile("[a-z]");
        Pattern specialCharacterPattern = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]");

        Matcher number = numberPattern.matcher(password);
        Matcher uppercase = uppercasePattern.matcher(password);
        Matcher lowercase = lowercasePattern.matcher(password);
        Matcher specialCharacter = specialCharacterPattern.matcher(password);

        return number.find() && uppercase.find() && lowercase.find() && specialCharacter.find();
    }

}
