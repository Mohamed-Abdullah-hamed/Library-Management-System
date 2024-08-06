package com.main.library.utill;

import java.util.regex.Pattern;

import com.main.library.annotation.ValidIdentityNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentityNumberValidator implements ConstraintValidator<ValidIdentityNumber, String> {

    private static final String ID_NUMBER_PATTERN = "^784-?[0-9]{4}-?[0-9]{7}-?[0-9]{1}$"; // Example pattern, adjust as needed

    @Override
    public void initialize(ValidIdentityNumber constraintAnnotation) {
        // Initialization logic if needed
    }

    @Override
    public boolean isValid(String identityNumber, ConstraintValidatorContext context) {
        if (identityNumber == null || identityNumber.isEmpty()) {
            return false; 
        }
        return Pattern.matches(ID_NUMBER_PATTERN, identityNumber);
    }
}
