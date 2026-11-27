package com.example.spring_xp_monolith.dto.products;

import java.util.List;

import com.example.spring_xp_monolith.Enums.Products.ClassType;
import com.example.spring_xp_monolith.Enums.Products.FoodType;
import com.example.spring_xp_monolith.Validators.CategoryValidators.ValidateCategoryById.ValidateCategoryById;
import com.example.spring_xp_monolith.Validators.OutletValidators.ValidateExcludedOutlets.ValidateExcludedOutlets;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data 
public class CreateProductRequestDto {
    @NotNull (message = "Category ID is required")
    @ValidateCategoryById 
    private Long categoryId;

    @NotBlank (message = "Product name is required")
    private String name;

    private String description;

    // private String displayName;

    // private String coverPicture;

    @PositiveOrZero(message = "Product cost cannot be negative")
    private Integer productCost = 0;

    @PositiveOrZero(message = "MRP cannot be negative")
    private Integer mrp;

    @PositiveOrZero(message = "Offer price cannot be negative")
    private Integer offerPrice;

    private String hsn;

    private String taxPercent;

    // Stores minutes
    private String makingTime;

    private Boolean hasAddon = false;

    private FoodType foodType;

    private ClassType classType;

    @PositiveOrZero(message = "Calories cannot be negative")
    private Integer calories;

    @PositiveOrZero(message = "Weight cannot be negative")
    private Integer weight;

    private List<String> keywords;

    private Integer sequenceNumber;

    private Boolean isComboProduct = false;

    private Boolean isFreebieProduct = false;

    private List<String> productsNature;

    private Boolean isStatusAutoUpdated = false;

    @Valid 
    @ValidateExcludedOutlets 
    private List<Long> excludedOutletIds;
}
