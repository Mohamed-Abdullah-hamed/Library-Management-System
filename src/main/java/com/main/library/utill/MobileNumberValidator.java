package com.main.library.utill;

import java.util.regex.Pattern;

import com.main.library.annotation.ValidMobileNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements ConstraintValidator<ValidMobileNumber, String> {

    private static final String MOBILE_NUMBER_PATTERN = "^(?:\\+971|00971|0)?(?:50|51|52|55|56|2|3|4|6|7|9)\\d{7}$"; // Adjust pattern as needed

    @Override
    public void initialize(ValidMobileNumber constraintAnnotation) {
        // Initialization logic if needed
    }

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            return false; 
        }
        return Pattern.matches(MOBILE_NUMBER_PATTERN, mobileNumber);
    }
}