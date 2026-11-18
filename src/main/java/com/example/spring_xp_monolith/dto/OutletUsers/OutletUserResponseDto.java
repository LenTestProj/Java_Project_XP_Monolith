package com.example.spring_xp_monolith.dto.OutletUsers;

import com.example.spring_xp_monolith.Enums.Custom.CustomEnums.Status;
import com.example.spring_xp_monolith.Enums.OutletUserEnum.Role;
import com.example.spring_xp_monolith.models.OutletUsers;

import lombok.Data;

@Data
public class OutletUserResponseDto {
    private Long id;
    private Long outletId;
    private Integer serialNumber;
    private String idNumber;
    private String name;
    private Role role;
    private String mobile;
    private String email;
    private Status status;

    public static OutletUserResponseDto from(OutletUsers u) {
        OutletUserResponseDto dto = new OutletUserResponseDto();
        dto.setId(u.getId());
        if (u.getOutlet() != null) dto.setOutletId(u.getOutlet().getId());
        dto.setSerialNumber(u.getSerialNumber());
        dto.setIdNumber(u.getIdNumber());
        dto.setName(u.getName());
        dto.setRole(u.getRole());
        dto.setMobile(u.getMobile());
        dto.setEmail(u.getEmail());
        dto.setStatus(u.getStatus());
        return dto;
    }
}
