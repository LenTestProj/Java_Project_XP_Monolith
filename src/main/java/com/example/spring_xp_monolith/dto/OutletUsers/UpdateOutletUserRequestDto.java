package com.example.spring_xp_monolith.dto.OutletUsers;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode (callSuper = true)
public class UpdateOutletUserRequestDto extends AddOutletUserRequestDto{
    @NotNull(message = "Outlet Id is required")
    private Long id;
}
