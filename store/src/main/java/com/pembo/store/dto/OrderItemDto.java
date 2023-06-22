package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;


public record OrderItemDto(Long id, @NotNull BigDecimal unitPrice, @NotNull Integer quantity, ProductDto product) implements Serializable {
}
