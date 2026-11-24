package com.example.spring_xp_monolith.dto.menuGroups;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateMenuGroupRequestDto extends AddMenuGroupRequestDto {
    @NotNull(message = "Menu group id is required")
    private Long id;
}
