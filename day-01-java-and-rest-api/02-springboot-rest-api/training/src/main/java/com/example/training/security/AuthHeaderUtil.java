package com.example.training.security;

public class AuthHeaderUtil {
    private AuthHeaderUtil() {
    }

    public static String extractToken(String authorizationHeader) {

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            throw new RuntimeException("Invalid Authorization Header");
        }

        return authorizationHeader.substring(7);
    }
}
