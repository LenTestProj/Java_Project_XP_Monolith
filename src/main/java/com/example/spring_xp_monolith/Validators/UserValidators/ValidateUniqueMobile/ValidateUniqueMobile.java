package com.example.spring_xp_monolith.Validators.UserValidators.ValidateUniqueMobile;

import jakarta.validation.Payload;

public @interface ValidateUniqueMobile {
    String message() default "Mobile number is already registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
