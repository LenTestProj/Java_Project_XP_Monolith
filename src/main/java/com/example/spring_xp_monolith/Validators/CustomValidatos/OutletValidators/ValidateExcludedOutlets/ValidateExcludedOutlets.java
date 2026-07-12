package com.example.spring_xp_monolith.Validators.CustomValidatos.OutletValidators.ValidateExcludedOutlets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = ValidateExcludedOutletsValidator.class)
public @interface ValidateExcludedOutlets {
    String message() default "Outlet does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
