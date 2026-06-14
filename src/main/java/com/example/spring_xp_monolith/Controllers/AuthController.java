package com.example.spring_xp_monolith.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_xp_monolith.Services.AuthService;
import com.example.spring_xp_monolith.dto.adminUsers.CreateAdminUserRequestDto;
import com.example.spring_xp_monolith.models.AdminUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-users")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<AdminUser> getUsers(@RequestParam(required = false) Long id){
        return authService.getAdminUsers(id);
    }

    @PostMapping
    public AdminUser createAdminUser(
        @Valid
        @RequestBody CreateAdminUserRequestDto request
    ){
        AdminUser newAdminUser = new AdminUser(request);
        return authService.createAdminUser(newAdminUser);
    }

    @PutMapping
    public AdminUser updateAdminUser(
        @Valid
        AdminUser
        request
    ){
        return authService.updateAdminUser(request.getId(), request);
    }

    @DeleteMapping
    public AdminUser deleteAdminUser(
        @Valid @RequestBody
        Long id
    ){
        return authService.deleteAdminUser(id);
    }
}
