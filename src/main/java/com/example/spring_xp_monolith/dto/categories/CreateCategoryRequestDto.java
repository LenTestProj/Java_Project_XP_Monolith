package com.example.spring_xp_monolith.dto.categories;

import com.example.spring_xp_monolith.models.Categories;

import jakarta.validation.constraints.NotBlank;
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

    private Integer sequenceNumber;

    private Categories.Status status;

    private Boolean isEventCategory;

    private Boolean isComboCategory;

    private Categories.OrderType orderType;

    private Boolean isNewCategory;

    @Pattern(
        regexp = "^(?!\\s*$).+",
        message = "Optional field cannot be blank"
    )
    private String someOptionalField;
}
