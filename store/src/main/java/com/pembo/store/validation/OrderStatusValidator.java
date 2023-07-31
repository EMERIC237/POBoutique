package com.pembo.store.validation;

import com.pembo.store.model.OrderStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class OrderStatusValidator implements ConstraintValidator<ValidOrderStatus, String> {
    @Override
    public void initialize(ValidOrderStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        if (status == null) return true;
        return Arrays.stream(OrderStatus.values())
                .anyMatch(e -> e.name().equalsIgnoreCase(status));
    }
}
