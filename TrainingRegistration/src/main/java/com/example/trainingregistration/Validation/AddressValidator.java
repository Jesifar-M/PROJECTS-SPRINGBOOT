package com.example.trainingregistration.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddressValidator
        implements ConstraintValidator<Address, String> {

    @Override
    public boolean isValid(String address,
                           ConstraintValidatorContext context) {

        if (address == null || address.isBlank()) {
            return false;
        }

        return address.contains("India");
    }
}