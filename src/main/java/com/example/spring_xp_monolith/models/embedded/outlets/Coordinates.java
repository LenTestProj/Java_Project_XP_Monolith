package com.example.spring_xp_monolith.models.embedded.outlets;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Coordinates {
    private String latitude;
    private String longitude;
}
