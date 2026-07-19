package com.example.spring_xp_monolith.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-users")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

}
