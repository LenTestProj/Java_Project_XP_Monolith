package com.example.spring_xp_monolith.models.embedded.Users;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor  
public class Device {
    private String name;
    private String deviceId;
}
