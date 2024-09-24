package com.example.service;

import com.example.entity.User;
import com.example.entity.UserPrincipal;
import com.example.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo repo;

    public MyUserDetailsService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = repo.findByName(name);

        if (user == null) {
            throw new UsernameNotFoundException("bad credentials");
        }

        return new UserPrincipal(user);
    }
}
