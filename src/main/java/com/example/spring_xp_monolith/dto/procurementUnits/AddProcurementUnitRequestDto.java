package com.example.spring_xp_monolith.dto.procurementUnits;

import com.example.spring_xp_monolith.Enums.ProcurementEnum.Types;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddProcurementUnitRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    private Types type;

    @NotBlank(message = "Contact person is required")
    private String contactPerson;

    @NotNull(message = "Mobile is required")
    private Integer mobile;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Pincode is required")
    private Integer pincode;

    @NotBlank(message = "State is required")
    private String state;
}
