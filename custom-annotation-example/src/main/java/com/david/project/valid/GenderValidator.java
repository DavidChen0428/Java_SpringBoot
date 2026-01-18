package com.david.project.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class GenderValidator implements ConstraintValidator<Gender, String> {

    private static final Set<String> ALLOWED_VALUES = Set.of("MALE", "FEMALE", "OTHER");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }
        return ALLOWED_VALUES.contains(value);
    }
}
