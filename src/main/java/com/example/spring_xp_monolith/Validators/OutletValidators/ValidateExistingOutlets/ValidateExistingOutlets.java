package com.example.spring_xp_monolith.Validators.OutletValidators.ValidateExistingOutlets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = ValidatingExistingOutletValidator.class)
public @interface ValidateExistingOutlets {
    String message() default "Outlet Id does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
