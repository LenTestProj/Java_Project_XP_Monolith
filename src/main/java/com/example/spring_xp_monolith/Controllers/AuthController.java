package com.example.spring_xp_monolith.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_xp_monolith.Services.AuthService;
import com.example.spring_xp_monolith.dto.Login.LoginRequestDto;


@RestController 
@RequestMapping ("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private AuthService authService;
    
    public AuthController(AuthenticationManager authenticationManager, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }
    
    @PostMapping ("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginRequestDto request){
        long oneDay = 24*60*60*1000L;
    
        String token = authService.login(request.getEmail(), request.getPassword(),"A",oneDay);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping ("/pos/login")
    public ResponseEntity<?> posLogin(@RequestBody LoginRequestDto request){
        long oneDay = 24*60*60*1000L;
    
        String token = authService.login(request.getEmail(), request.getPassword(),"U",oneDay);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping ("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequestDto request){
        String token = authService.login(request.getEmail(), request.getPassword(),"U",null);
        return ResponseEntity.ok().body(token);
    }
}
