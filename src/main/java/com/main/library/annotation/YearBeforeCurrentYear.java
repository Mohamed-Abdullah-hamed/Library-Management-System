package com.main.library.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.main.library.utill.YearBeforeCurrentYearValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = YearBeforeCurrentYearValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface YearBeforeCurrentYear {
    String message() default "Year must be before the current year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}