package com.example.spring_xp_monolith.dto;

public class ErrorResponse {
    private final boolean status = false;
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
