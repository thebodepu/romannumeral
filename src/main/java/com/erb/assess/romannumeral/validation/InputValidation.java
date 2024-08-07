package com.erb.assess.romannumeral.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomInputValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InputValidation {
    String message() default "Invalid number combination";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
