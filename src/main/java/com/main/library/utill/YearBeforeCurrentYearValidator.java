package com.main.library.utill;

import java.time.Year;

import com.main.library.annotation.YearBeforeCurrentYear;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class YearBeforeCurrentYearValidator implements ConstraintValidator<YearBeforeCurrentYear, Integer> {

    @Override
    public void initialize(YearBeforeCurrentYear constraintAnnotation) {
        // Initialization code if needed
    }

    @Override
    public boolean isValid(Integer publicationYear, ConstraintValidatorContext context) {
        if (publicationYear == null) {
            return false; 
        }
        return publicationYear <= Year.now().getValue();
    }
}
