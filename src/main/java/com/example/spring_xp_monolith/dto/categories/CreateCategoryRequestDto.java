package com.example.spring_xp_monolith.dto.categories;

import java.util.List;

import com.example.spring_xp_monolith.Enums.Products.FoodType;
import com.example.spring_xp_monolith.Validators.OutletValidators.ValidateExcludedOutlets.ValidateExcludedOutlets;
import com.example.spring_xp_monolith.models.Categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

// Sent as multipart/form-data; displayImage and bannerImage are separate file parts.
@Data
public class CreateCategoryRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Sequence number is required")
    @Positive(message = "Sequence number must be greater than zero")
    private Integer sequenceNumber;

    private Categories.Status status;

    private Boolean isComboCategory;

    @NotNull(message = "Order type is required")
    private Categories.OrderType orderType;

    private Boolean isStatusAutoUpdated;

    private List<FoodType> foodType;

    @ValidateExcludedOutlets
    private List<Long> excludedOutletIds;
}
