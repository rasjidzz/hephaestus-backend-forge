package com.example.training.security;

import com.example.training.user.entity.Role;
import com.example.training.user.entity.User;

public class RoleValidator {
     public static void validate(
            User user,
            Role... allowedRoles) {

        for (Role role : allowedRoles) {

            if (role.equals(user.getRole())) {
                return;
            }
        }

        throw new RuntimeException("Forbidden");
    }
    
}
