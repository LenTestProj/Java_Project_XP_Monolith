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

import com.example.spring_xp_monolith.Services.MenuGroupService;
import com.example.spring_xp_monolith.dto.ApiResponse;
import com.example.spring_xp_monolith.dto.menuGroups.AddMenuGroupRequestDto;
import com.example.spring_xp_monolith.dto.menuGroups.MenuGroupResponseDto;
import com.example.spring_xp_monolith.dto.menuGroups.UpdateMenuGroupRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/menu-groups")
@RequiredArgsConstructor
public class MenuGroupController {

    private final MenuGroupService menuGroupService;

    @PostMapping
    public ResponseEntity<ApiResponse<MenuGroupResponseDto>> createMenuGroup(
            @Valid @RequestBody AddMenuGroupRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(menuGroupService.createMenuGroup(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MenuGroupResponseDto>>> getMenuGroups() {
        return ResponseEntity.ok(ApiResponse.success(menuGroupService.getMenuGroups()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuGroupResponseDto>> getMenuGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(menuGroupService.getMenuGroupById(id)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<MenuGroupResponseDto>> updateMenuGroup(
            @Valid @RequestBody UpdateMenuGroupRequestDto request) {
        return ResponseEntity.ok(ApiResponse.success(menuGroupService.updateMenuGroup(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuGroupResponseDto>> deleteMenuGroup(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(menuGroupService.deleteMenuGroup(id)));
    }
}
