package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link com.pembo.store.model.Cart}
 */
@Value
public class CartDto implements Serializable {
    Long id;
    Long userId;
    String userUsername;
    @NotNull
    Set<CartItemDto> cartItems;
    BigDecimal totalPrice;
}