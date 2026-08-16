package com.example.spring_xp_monolith.dto.outlets;

import java.util.List;

import com.example.spring_xp_monolith.Enums.Custom.CustomEnums.Status;
import com.example.spring_xp_monolith.Validators.CustomValidatos.GstValidator.ValidateGST;
import com.example.spring_xp_monolith.Validators.MenuGroupValidators.ValidateExistingMenuGroup;
import com.example.spring_xp_monolith.models.Categories.OrderType;
import com.example.spring_xp_monolith.models.embedded.outlets.Coordinates;
import com.example.spring_xp_monolith.models.embedded.outlets.PinelabCredentials;
import com.example.spring_xp_monolith.models.embedded.outlets.Platform;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddOutletRequestDto {
    @NotBlank(message = "Outlet name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^[0-9](10)$",
        message = "Phone number must contain 10 digits"
    )
    private String phone;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "Pincode is required")
    @Pattern(
        regexp = "^[0-9]{6}$",
        message = "Pincode must contain exactly 6 digits"
    )
    private String pincode;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Status is required")
    private Status status;

    @NotNull(message = "Payment type is required")
    private com.example.spring_xp_monolith.Enums.OutletEnum.PaymentType paymentType;

    @NotNull(message = "Order accept type is required")
    private OrderType orderAcceptType;

        @NotNull(message = "Coordinates are required")
    @Valid
    private Coordinates coordinates;

    private List<@Valid Platform> platforms;

    private Boolean isEventOutlet;

    @Size(
        min = 15,
        max = 15,
        message = "GST number should be of length 15"
    )
    @ValidateGST
    private String gstin;

    @Valid
    private PinelabCredentials pineLabCredentials;

    @NotNull(message = "Type field is required")
    @Pattern(
        regexp = "COMPANY_OWNED|FRANCHISE|EVENT",
        message = "Type should be COMPANY_OWNED, FRANCHISE or EVENT"
    )
    private String type;

    @NotNull(message = "Menu Group is required")
    @ValidateExistingMenuGroup
    private String menuGroup;

    private String franchiseName;

    private String franchisePhone;

    private String franchiseEmail;

    @AssertTrue(message = "Outlet validation failed")
    public boolean isValidOutlet() {
        return validateOutletData();
    }

    private boolean validateOutletData() {
        return true;
    }
}
