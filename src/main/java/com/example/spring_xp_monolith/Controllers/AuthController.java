package com.example.spring_xp_monolith.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_xp_monolith.dto.Login.LoginRequestDto;
import com.example.spring_xp_monolith.dto.Login.LoginResponseDto;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    
    // @PostMapping("/login")
    // public LoginResponseDto login(@RequestBody LoginRequestDto request){
    //     Authentication authentication = authenticationManager.authenticate(
    //         new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    //     );

    //     String token = jwtSer
    // }
}
