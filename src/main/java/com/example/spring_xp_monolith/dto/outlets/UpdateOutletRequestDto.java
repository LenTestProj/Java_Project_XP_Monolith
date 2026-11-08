package com.example.spring_xp_monolith.dto.outlets;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(callSuper = true) 
public class UpdateOutletRequestDto extends AddOutletRequestDto {
    @NotNull (message = "Outlet Id is required")
    private Long id;
}
