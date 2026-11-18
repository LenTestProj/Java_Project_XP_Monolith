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
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_xp_monolith.Services.OutletService;
import com.example.spring_xp_monolith.dto.ApiResponse;
import com.example.spring_xp_monolith.dto.outlets.AddOutletRequestDto;
import com.example.spring_xp_monolith.dto.outlets.OutletResponseDto;
import com.example.spring_xp_monolith.dto.outlets.UpdateOutletRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/outlets")
@RequiredArgsConstructor
public class OutletController {

    private final OutletService outletService;

    @PostMapping
    public ResponseEntity<ApiResponse<OutletResponseDto>> createOutlet(
            @Valid @RequestBody AddOutletRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(outletService.createOutlet(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OutletResponseDto>>> getOutlets() {
        return ResponseEntity.ok(ApiResponse.success(outletService.getOutlets()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OutletResponseDto>> getOutletById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(outletService.getOutletById(id)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<OutletResponseDto>> updateOutlet(
            @Valid @RequestBody UpdateOutletRequestDto request) {
        return ResponseEntity.ok(ApiResponse.success(outletService.updateOutlet(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<OutletResponseDto>> deleteOutlet(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(outletService.deleteOutlet(id)));
    }
}
