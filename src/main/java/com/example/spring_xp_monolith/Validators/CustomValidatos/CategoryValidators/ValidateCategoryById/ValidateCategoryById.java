package com.example.spring_xp_monolith.Validators.CustomValidatos.CategoryValidators.ValidateCategoryById;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ValidateCategoryByIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidateCategoryById {
    String message() default "Category Id does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
