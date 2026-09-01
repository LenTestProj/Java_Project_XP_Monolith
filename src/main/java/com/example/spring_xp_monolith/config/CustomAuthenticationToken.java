package com.example.spring_xp_monolith.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken
        extends UsernamePasswordAuthenticationToken {

    private final String role;

    public CustomAuthenticationToken(
            String username,
            String password,
            String role) {

        super(username, password);

        this.role = role;
    }

    public String getRole() {
        return role;
    }
}