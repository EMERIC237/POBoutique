package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.pembo.store.model.OrderItem}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public record OrderItemDto(Long id, @NotNull BigDecimal unitPrice, @NotNull Integer quantity, String productName,
                           String productImageUrl) implements Serializable {
}