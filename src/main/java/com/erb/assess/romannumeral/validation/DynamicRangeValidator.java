package com.erb.assess.romannumeral.validation;

import org.springframework.beans.factory.annotation.Value;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DynamicRangeValidator implements ConstraintValidator<DynamicRange, Integer> {

    @Value("${validation.roman.number.min}")
    private int min;

    @Value("${validation.roman.number.max}")
    private int max;

    @Override
    public void initialize(DynamicRange constraintAnnotation) {
        // No need to initialize min and max here, they will be injected by Spring
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value >= min && value <= max;
    }
}

