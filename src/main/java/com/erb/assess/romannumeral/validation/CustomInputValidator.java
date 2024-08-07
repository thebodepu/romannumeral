package com.erb.assess.romannumeral.validation;

import com.erb.assess.romannumeral.dto.InputParams;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class CustomInputValidator implements ConstraintValidator<InputValidation, InputParams> {
    @Value("${validation.roman.number.min}")
    private int min;

    @Value("${validation.roman.number.max}")
    private int max;

    @Override
    public void initialize(InputValidation constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(InputParams value, ConstraintValidatorContext context) {
        if (value.getQuery() != null) {
            return value.getQuery() >= min && value.getQuery() <= max
                    && value.getMin() == null && value.getMax() == null;
        } else if (value.getMin() != null && value.getMax() != null) {
            return value.getMin() >= min && value.getMin() <= max
                    && value.getMax() >= min && value.getMax() <= max
                    && value.getQuery() == null;
        } else {
            return false; // Invalid if neither condition is met
        }
    }
}

