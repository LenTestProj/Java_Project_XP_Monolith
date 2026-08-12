package com.example.spring_xp_monolith.dto.Users;

import java.util.List;

import com.example.spring_xp_monolith.Validators.CustomValidatos.NameValidators.ValidateName;
import com.example.spring_xp_monolith.models.embedded.Users.Device;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAccountDto {
    @NotBlank(message = "Name is required")
    @ValidateName
    private String name;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp="^[0-9]+$",
        message = "Mobile number must be a number"
    )
    @Size(min=10, max=10, message = "Mobile must be 10 digit")
    private String mobile;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min=3, message = "Password should have a minimum of 3 characters")
    private String password;

    @NotBlank(message = "Signup type field is required")
    @Pattern(
        regexp = "^(REGULAR|GOOGLE|APPLE)$",
        message= "Signup type should be either of the three values - REGULAR, GOOGLE or APPLE"
    )
    private String signupType;

    private List<Device> devices;

    private String referringUser;

    private String referringUserMobile;
}
