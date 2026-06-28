package com.example.spring_xp_monolith.models.embedded.outlets;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Platform {
    private String name;

    private String webLink;
}
