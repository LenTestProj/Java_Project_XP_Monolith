package com.example.spring_xp_monolith.dto.OutletUsers;

import com.example.spring_xp_monolith.Enums.Custom.CustomEnums.Status;
import com.example.spring_xp_monolith.Enums.OutletUserEnum.Role;
import com.example.spring_xp_monolith.Validators.CustomValidatos.NameValidators.ValidateName;
import com.example.spring_xp_monolith.Validators.OutletValidators.ValidateExistingOutlets.ValidateExistingOutlets;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateOutletUserRequestDto {
    @NotNull(message = "Outlet user Id is required")
    private Long id;

    @NotNull(message = "Outlet Id is required")
    @ValidateExistingOutlets
    private Long outletId;

    @NotNull(message = "Serial number is required")
    private Integer serialNumber;

    @NotBlank(message = "Id number is required")
    private String idNumber;

    @NotBlank(message = "Name is required")
    @ValidateName
    private String name;

    @NotNull(message = "Role should be one of CASHIER, MANAGER or CAFE_MANAGER")
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

    // Optional on update - only re-hashed when provided
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
        regexp = "^[^\\s]+$",
        message = "Password field should not contain spaces"
    )
    private String password;

    private Status status;
}
