package com.example.spring_xp_monolith.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_xp_monolith.Services.UserService;
import com.example.spring_xp_monolith.dto.Users.CreateAccountDto;
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
}
