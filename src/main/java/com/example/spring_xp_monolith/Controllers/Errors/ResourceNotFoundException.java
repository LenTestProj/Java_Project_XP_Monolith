package com.example.spring_xp_monolith.Controllers.Errors;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
