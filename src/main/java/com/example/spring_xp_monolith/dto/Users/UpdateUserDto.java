
package com.example.spring_xp_monolith.dto.Users;

import java.util.List;

import com.example.spring_xp_monolith.Enums.Users.SignupType;
import com.example.spring_xp_monolith.models.embedded.Users.Device;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDto {

    private Long id;

    private String name;

    @Pattern(
        regexp = "^[0-9]+$",
        message = "Mobile number must be a number"
    )
    @Size(min = 10, max = 10, message = "Mobile must be 10 digit")
    private String mobile;

    @Email(message = "Please enter a valid email format")
    private String email;

    @Size(min = 3, message = "Password should have a minimum of 3 characters")
    private String password;

    private SignupType signupType;

    private List<Device> devices;

    private String referringUser;

    private String referringUserMobile;

    private String outletId;
}
