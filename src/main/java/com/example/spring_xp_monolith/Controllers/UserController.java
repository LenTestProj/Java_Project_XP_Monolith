package com.example.spring_xp_monolith.Controllers;

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

import com.example.spring_xp_monolith.Services.UserService;
import com.example.spring_xp_monolith.dto.Users.CreateAccountDto;
import com.example.spring_xp_monolith.dto.Users.UpdateUserDto;
import com.example.spring_xp_monolith.models.Users;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping 
    public ResponseEntity<?> addUser(@Valid @RequestBody CreateAccountDto request){
        Users userResponse = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @GetMapping
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        Users userResponse = userService.getUserById(userId);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Update User
     */
    @PutMapping
    public ResponseEntity<?> updateUser(
            @Valid @RequestBody UpdateUserDto request) {

        Users userResponse = userService.updateUser(request);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Delete User
     */
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Users userResponse = userService.deleteUser(userId);
        return ResponseEntity.ok(userResponse);
    }
}
