package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;


public record OrderItemDto(Long id, @NotNull BigDecimal unitPrice, @NotNull @Positive Integer quantity,
                           @NotNull ProductDto product) implements Serializable {
}
