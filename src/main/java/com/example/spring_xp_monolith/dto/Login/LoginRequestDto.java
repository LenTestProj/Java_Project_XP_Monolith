package com.example.spring_xp_monolith.dto.Login;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
