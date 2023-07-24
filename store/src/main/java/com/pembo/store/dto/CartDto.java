package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import lombok.Data;

/**
 * DTO for {@link com.pembo.store.model.Cart}
 */
@Data
public class CartDto implements Serializable {
    private Long id;
    private Long userId;
    private String userUsername;
    @NotNull
    private Set<CartItemDto> cartItems;
    private BigDecimal totalPrice;

    public CartDto(Long id, Long userId, String userUsername, @NotNull Set<CartItemDto> cartItems,
                   BigDecimal totalPrice) {
        this.id = id;
        this.userId = userId;
        this.userUsername = userUsername;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }
}
