package com.erb.assess.romannumeral.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DynamicRangeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicRange {
    String message() default "Value is out of range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}

