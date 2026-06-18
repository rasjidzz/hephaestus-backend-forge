package com.example.training.security;

import com.example.training.exception.UnauthorizedException;

public class AuthHeaderUtil {
    private AuthHeaderUtil() {
    }

    public static String extractToken(String authorizationHeader) {

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            throw new UnauthorizedException(
                    "Authentication is required");
        }

        return authorizationHeader.substring(7);
    }
}
