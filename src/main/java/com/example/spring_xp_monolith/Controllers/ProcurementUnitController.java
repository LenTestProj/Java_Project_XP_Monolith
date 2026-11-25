package com.example.spring_xp_monolith.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_xp_monolith.Enums.ProcurementEnum.Types;
import com.example.spring_xp_monolith.Services.ProcurementUnitService;
import com.example.spring_xp_monolith.dto.ApiResponse;
import com.example.spring_xp_monolith.dto.procurementUnits.AddProcurementUnitRequestDto;
import com.example.spring_xp_monolith.dto.procurementUnits.ProcurementUnitResponseDto;
import com.example.spring_xp_monolith.dto.procurementUnits.UpdateProcurementUnitRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/procurement-units")
@RequiredArgsConstructor
public class ProcurementUnitController {

    private final ProcurementUnitService procurementUnitService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProcurementUnitResponseDto>> createProcurementUnit(
            @Valid @RequestBody AddProcurementUnitRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(procurementUnitService.createProcurementUnit(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProcurementUnitResponseDto>>> getProcurementUnits(
            @RequestParam(required = false) Types type) {
        return ResponseEntity.ok(ApiResponse.success(procurementUnitService.getProcurementUnits(type)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProcurementUnitResponseDto>> getProcurementUnitById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(procurementUnitService.getProcurementUnitById(id)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ProcurementUnitResponseDto>> updateProcurementUnit(
            @Valid @RequestBody UpdateProcurementUnitRequestDto request) {
        return ResponseEntity.ok(ApiResponse.success(procurementUnitService.updateProcurementUnit(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProcurementUnitResponseDto>> deleteProcurementUnit(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(procurementUnitService.deleteProcurementUnit(id)));
    }
}
