package com.example.spring_xp_monolith.models.embedded.Users;

import com.example.spring_xp_monolith.models.Users;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Device {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String deviceId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;
}
