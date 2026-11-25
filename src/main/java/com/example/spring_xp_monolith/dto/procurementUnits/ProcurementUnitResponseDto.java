package com.example.spring_xp_monolith.dto.procurementUnits;

import java.time.LocalDateTime;

import com.example.spring_xp_monolith.Enums.ProcurementEnum.Types;
import com.example.spring_xp_monolith.models.ProcurementUnits;

import lombok.Data;

@Data
public class ProcurementUnitResponseDto {
    private Long id;
    private String name;
    private Types type;
    private String contactPerson;
    private Integer mobile;
    private String email;
    private String address;
    private String city;
    private Integer pincode;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProcurementUnitResponseDto from(ProcurementUnits p) {
        ProcurementUnitResponseDto dto = new ProcurementUnitResponseDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setType(p.getType());
        dto.setContactPerson(p.getContactPerson());
        dto.setMobile(p.getMobile());
        dto.setEmail(p.getEmail());
        dto.setAddress(p.getAddress());
        dto.setCity(p.getCity());
        dto.setPincode(p.getPincode());
        dto.setState(p.getState());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        return dto;
    }
}
