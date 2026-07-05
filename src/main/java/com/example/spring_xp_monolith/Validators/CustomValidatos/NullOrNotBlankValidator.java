package com.example.spring_xp_monolith.Validators.CustomValidatos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        // null is allowed
        if (value == null) {
            return true;
        }

        // Reject "" and "   "
        return !value.trim().isEmpty();
    }
}
