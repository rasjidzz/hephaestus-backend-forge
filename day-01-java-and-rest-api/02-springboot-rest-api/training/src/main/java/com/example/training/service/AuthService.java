package com.example.training.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.training.dto.LoginRequest;
import com.example.training.security.AuthUtils;
import com.example.training.user.entity.Role;
import com.example.training.user.entity.User;

@Service
public class AuthService {
    private final List<User> users = List.of(
            new User(
                        UUID.randomUUID(),
                        "admin@mail.com",
                        "admin123",
                        Role.ADMIN
                ),
                new User(
                        UUID.randomUUID(),
                        "staff@mail.com",
                        "staff123",
                        Role.STAFF
                ),
                new User(
                        UUID.randomUUID(),
                        "approver@mail.com",
                        "approver123",
                        Role.APPROVER
                ),
                new User(
                        UUID.randomUUID(),
                        "manager@mail.com",
                        "manager123",
                        Role.MANAGER
                )
        );
    public User findByEmail(String email) {

        return users.stream()
                .filter(u ->
                        u.getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }

    public String login(LoginRequest request) {

        User user = users.stream()
                .filter(u ->
                        u.getEmail().equals(request.getEmail())
                                && u.getPasswordHash().equals(request.getPassword()))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Invalid credential"));

        return AuthUtils.generateToken(
                user.getEmail(),
                user.getRole());
    }
    public User validateToken(String token) {
        try {
            String decoded =
                    AuthUtils.decodeToken(token);

            String email =
                    decoded.split(":")[0];

            return findByEmail(email);

        } catch (Exception ex) {

            throw new RuntimeException("Unauthorized");
        }
    }
}
