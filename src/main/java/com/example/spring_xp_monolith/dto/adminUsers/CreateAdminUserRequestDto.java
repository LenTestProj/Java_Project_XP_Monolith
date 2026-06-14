package com.example.spring_xp_monolith.dto.adminUsers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAdminUserRequestDto {
    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Mobile is required")
    private String mobile;

    @Email(message = "Invalid email")
    private String email;

    @Size(min=3, message = "Password must be minimum three characters")
    private String password;

}
