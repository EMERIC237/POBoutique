package com.pembo.store.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.pembo.store.model.CartItem}
 */
@Value
public class CartItemDto implements Serializable {
    Long id;
    Long productId;
    String productName;
    String productDescription;
    BigDecimal productPrice;
    String productImageUrl;
    @Positive
    @PositiveOrZero(message = "Quantity should be at least one")
    Integer quantity;
}