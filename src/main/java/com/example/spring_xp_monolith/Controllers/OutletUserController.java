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

import com.example.spring_xp_monolith.Services.OutletUserService;
import com.example.spring_xp_monolith.dto.ApiResponse;
import com.example.spring_xp_monolith.dto.OutletUsers.AddOutletUserRequestDto;
import com.example.spring_xp_monolith.dto.OutletUsers.OutletUserResponseDto;
import com.example.spring_xp_monolith.dto.OutletUsers.UpdateOutletUserRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/outlet-users")
@RequiredArgsConstructor
public class OutletUserController {

    private final OutletUserService outletUserService;

    @PostMapping
    public ResponseEntity<ApiResponse<OutletUserResponseDto>> createOutletUser(
            @Valid @RequestBody AddOutletUserRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(outletUserService.createOutletUser(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OutletUserResponseDto>>> getOutletUsers(
            @RequestParam(required = false) Long outletId) {
        return ResponseEntity.ok(ApiResponse.success(outletUserService.getOutletUsers(outletId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OutletUserResponseDto>> getOutletUserById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(outletUserService.getOutletUserById(id)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<OutletUserResponseDto>> updateOutletUser(
            @Valid @RequestBody UpdateOutletUserRequestDto request) {
        return ResponseEntity.ok(ApiResponse.success(outletUserService.updateOutletUser(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<OutletUserResponseDto>> deleteOutletUser(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(outletUserService.deleteOutletUser(id)));
    }
}
