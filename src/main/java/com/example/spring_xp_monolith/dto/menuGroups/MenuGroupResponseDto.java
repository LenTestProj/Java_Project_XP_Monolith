package com.example.spring_xp_monolith.dto.menuGroups;

import java.time.LocalDateTime;

import com.example.spring_xp_monolith.models.MenuGroups;

import lombok.Data;

@Data
public class MenuGroupResponseDto {
    private Long id;
    private String groupName;
    private String description;
    private Boolean isDefaultGroup;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static MenuGroupResponseDto from(MenuGroups m) {
        MenuGroupResponseDto dto = new MenuGroupResponseDto();
        dto.setId(m.getId());
        dto.setGroupName(m.getGroupName());
        dto.setDescription(m.getDescription());
        dto.setIsDefaultGroup(m.getIsDefaultGroup());
        dto.setCreatedDate(m.getCreatedDate());
        dto.setUpdatedDate(m.getUpdatedDate());
        return dto;
    }
}
