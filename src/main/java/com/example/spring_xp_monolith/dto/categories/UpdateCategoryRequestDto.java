package com.example.spring_xp_monolith.dto.categories;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(callSuper = true) 
public class UpdateCategoryRequestDto extends CreateCategoryRequestDto{
    @NotNull(message  = "Category Id is required") 
    private Long id;
}
