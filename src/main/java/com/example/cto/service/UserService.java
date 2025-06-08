package com.example.cto.service;

import com.example.cto.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
