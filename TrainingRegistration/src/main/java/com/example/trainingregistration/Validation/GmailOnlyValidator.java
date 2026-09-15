package com.example.trainingregistration.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GmailOnlyValidator
        implements ConstraintValidator<GmailOnly, String> {

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext context) {

        if (email == null || email.isBlank()) {
            return false;
        }

        return email.endsWith("@gmail.com");
    }
}