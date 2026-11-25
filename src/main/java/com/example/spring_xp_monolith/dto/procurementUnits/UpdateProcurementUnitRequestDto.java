package com.example.spring_xp_monolith.dto.procurementUnits;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateProcurementUnitRequestDto extends AddProcurementUnitRequestDto {
    @NotNull(message = "Procurement unit id is required")
    private Long id;
}
