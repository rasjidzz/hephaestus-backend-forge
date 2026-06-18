package com.example.training.security;

import com.example.training.user.entity.User;

public class AuthContext {
    private final User user;
    public AuthContext(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
