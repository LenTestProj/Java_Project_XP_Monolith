package com.example.spring_xp_monolith.dto.outlets;

import java.util.List;

import com.example.spring_xp_monolith.Enums.Custom.CustomEnums.OrderType;
import com.example.spring_xp_monolith.Enums.Custom.CustomEnums.Status;
import com.example.spring_xp_monolith.Enums.OutletEnum.OutletSequence;
import com.example.spring_xp_monolith.Enums.OutletEnum.OutletType;
import com.example.spring_xp_monolith.Enums.OutletEnum.PaymentType;
import com.example.spring_xp_monolith.Validators.CustomValidatos.GstValidator.ValidateGST;
import com.example.spring_xp_monolith.Validators.MenuGroupValidators.ValidateExistingMenuGroup;
import com.example.spring_xp_monolith.models.embedded.outlets.Coordinates;
import com.example.spring_xp_monolith.models.embedded.outlets.PinelabCredentials;
import com.example.spring_xp_monolith.models.embedded.outlets.Platform;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
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
        regexp = "^[0-9]{10}$",
        message = "Phone number must contain 10 digits"
    )
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

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
    private PaymentType paymentType;

    @NotNull(message = "Order accept type is required")
    private OrderType orderAcceptType;

    @NotNull(message = "Coordinates are required")
    @Valid
    private Coordinates coordinates;

    @Valid
    private Coordinates location;

    private List<@Valid Platform> platforms;

    private Boolean isEventOutlet;

    @Size(
        min = 15,
        max = 15,
        message = "GST number should be of length 15"
    )
    @ValidateGST
    private String gstin;

    private String fssai;

    @Valid
    private PinelabCredentials pineLabCredentials;

    @NotNull(message = "Type should be COMPANY_OWNED, FRANCHISE or EVENT")
    private OutletType type;

    @NotNull(message = "Outlet number is required")
    private Integer outletNo;

    private OutletSequence outletSequence;

    @NotNull(message = "Menu Group is required")
    @ValidateExistingMenuGroup
    private Long menuGroupId;

    private Long foodUnitId;

    @NotNull(message = "Beverage unit is required")
    private Long beverageUnitId;

    @NotNull(message = "Central unit is required")
    private Long centralUnitId;

    private String outletImage;

    private String openingTime;

    private String closingTime;

    private String minimumDeliveryRadius;

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
