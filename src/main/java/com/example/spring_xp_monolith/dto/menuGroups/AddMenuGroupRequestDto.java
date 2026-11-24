package com.example.spring_xp_monolith.dto.menuGroups;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddMenuGroupRequestDto {
    @NotBlank(message = "Group name is required")
    private String groupName;

    @NotBlank(message = "Description is required")
    private String description;

    private Boolean isDefaultGroup;
}
