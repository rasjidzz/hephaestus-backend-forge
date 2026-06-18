package com.example.training.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.example.training.user.entity.Role;

public class AuthUtils {
    public static String generateToken(String email, Role role){
        String payload = email + ":" +role.name();
        return Base64.getEncoder().encodeToString(payload.getBytes(StandardCharsets.UTF_8));
    }
    public static String decodeToken(String token){
        return new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
    }
}
