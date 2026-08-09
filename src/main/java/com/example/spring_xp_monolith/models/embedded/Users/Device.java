package com.example.spring_xp_monolith.models.embedded.Users;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Device {
    private String name;
    private String deviceId;
}
