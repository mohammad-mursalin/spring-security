package com.mursalin.springSecurity.service;

import com.mursalin.springSecurity.model.Users;
import com.mursalin.springSecurity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {

    private UserRepository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }
}
