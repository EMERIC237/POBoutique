package com.pembo.store.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderStatusValidator.class)
public @interface ValidOrderStatus {

    String message() default "Invalid order status";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
