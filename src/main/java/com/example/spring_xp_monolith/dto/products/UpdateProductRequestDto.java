package com.example.spring_xp_monolith.dto.products;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode (callSuper = true)
public class UpdateProductRequestDto extends CreateProductRequestDto{
    @NotNull (message = "Product Id is required")
    private Long id;
}
