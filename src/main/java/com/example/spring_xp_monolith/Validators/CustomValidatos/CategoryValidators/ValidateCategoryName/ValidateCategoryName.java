package com.example.spring_xp_monolith.Validators.CustomValidatos.CategoryValidators.ValidateCategoryName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ValidateCategoryNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidateCategoryName {
    String message() default "Category Name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
