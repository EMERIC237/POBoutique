package com.pembo.store.validation;

import com.pembo.store.model.UserRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class UserRoleValidator implements ConstraintValidator<ValidUserRole, String> {

    @Override
    public void initialize(ValidUserRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        if (role == null) {
            return true;
        }
        return Arrays.stream(UserRole.values())
                     .anyMatch(e -> e.name()
                                     .equalsIgnoreCase(role));
    }
}
