package com.example.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.training.dto.LoginRequest;
import com.example.training.dto.LoginResponse;
import com.example.training.dto.UserResponse;
import com.example.training.security.AuthHeaderUtil;
import com.example.training.security.AuthUtils;
import com.example.training.service.AuthService;
import com.example.training.user.entity.User;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/auth")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        String token =
                authService.login(request);

        return new LoginResponse(token);
    }

    @GetMapping("/me")
    public UserResponse me(
            @RequestHeader("Authorization")
            String authorization) {

        String token =
                AuthHeaderUtil.extractToken(authorization);

        String decoded =
                AuthUtils.decodeToken(token);

        String email =
                decoded.split(":")[0];

        User user = authService.validateToken(token);

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
