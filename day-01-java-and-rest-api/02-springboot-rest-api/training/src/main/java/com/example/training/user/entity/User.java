package com.example.training.user.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;

    private String email;

    private String passwordHash;

    private Role role;

    private Boolean active = true;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    public User(
            UUID id,
            String email,
            String passwordHash,
            Role role) {

        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = true;
        this.createdAt = OffsetDateTime.now();
    }
}
