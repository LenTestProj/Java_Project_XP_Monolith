package com.example.spring_xp_monolith.Validators.CustomValidatos;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotBlank {

    String message() default "Value cannot be blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}