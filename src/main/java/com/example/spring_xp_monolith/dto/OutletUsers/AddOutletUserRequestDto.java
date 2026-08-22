package com.example.spring_xp_monolith.dto.OutletUsers;

import com.example.spring_xp_monolith.Enums.Custom.CustomEnums.Status;
import com.example.spring_xp_monolith.Enums.OutletUserEnum.Role;
import com.example.spring_xp_monolith.Validators.CustomValidatos.NullorNotBlank;
import com.example.spring_xp_monolith.Validators.CustomValidatos.NameValidators.ValidateName;
import com.example.spring_xp_monolith.Validators.OutletValidators.ValidateExistingOutlets.ValidateExistingOutlets;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddOutletUserRequestDto {
    @NotBlank(message = "Outlet Id should be blank")
    @ValidateExistingOutlets
    private Long outlet;

    @NotBlank(message = "Name is required")
    @ValidateName
    private String name;

    @NotBlank(message = "Role should either of the three values - CASHIER, MANAGER or CAFE-MANAGER")
    private Role role;

    @NotBlank(message="mobile number is required")
    @Pattern(
        regexp ="^[0-9]{10}$",
        message = "Phone number must contain 10 digits"
    )
    private String mobile;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 8)
    @Pattern(
        regexp = "^[^\\s]+$",
        message = "Password field should not contain spaces"
    )
    private String password;

    @NullorNotBlank
    private Status status;
}
