package com.example.spring_xp_monolith.dto.categories;

import java.util.List;

import com.example.spring_xp_monolith.Validators.CustomValidatos.OutletValidators.ValidateExcludedOutlets.ValidateExcludedOutlets;
import com.example.spring_xp_monolith.models.Categories;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateCategoryRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Display image is required")
    private String displayImage;

    @NotBlank(message = "Banner image is required")
    private String bannerImage;

    @NotNull(message = "Sequence number is required")
    @DecimalMin(value="0.0", inclusive = false, message="Seqeunce number must be greater than zero")
    private Integer sequenceNumber;

    private Categories.Status status;

    private Boolean isEventCategory;

    private Boolean isComboCategory;

    private Categories.OrderType orderType;

    private Boolean isNewCategory;

    @Valid
    @ValidateExcludedOutlets
    private List<Long> excludedOutlets;

    // @Pattern(
    //     regexp = "^(?!\\s*$).+",
    //     message = "Optional field cannot be blank"
    // )
    // private String someOptionalField;
}
