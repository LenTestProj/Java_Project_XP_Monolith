package com.example.spring_xp_monolith.dto;

public class ApiResponse<T> {
    private final boolean status;
    private final T data;

    private ApiResponse(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data);
    }

    public boolean getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
