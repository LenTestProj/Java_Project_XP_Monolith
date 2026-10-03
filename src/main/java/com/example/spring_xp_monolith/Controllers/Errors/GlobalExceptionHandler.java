package com.example.spring_xp_monolith.Controllers.Errors;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler (IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handleRequest(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message",ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGeneralException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message",ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleResourceNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message",ex.getMessage()));
    }

    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .map(error -> error.getField() + ": " + error.getDefaultMessage())
                            .findFirst()
                            .orElse("Validation Failed");

        return ResponseEntity.badRequest().body(Map.of("message",message));
    }                    
}
