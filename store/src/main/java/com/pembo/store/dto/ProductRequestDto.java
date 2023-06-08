package com.pembo.store.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link com.pembo.store.model.Product}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public record ProductRequestDto(Long id, @NotNull @Size(max = 255) String name, @Size(max = 255) String description,
                                @NotNull @PositiveOrZero(message = "price must be greater than zero") BigDecimal price,
                                @NotNull @PositiveOrZero(message = "stock quantity cannot be negative") Integer stockQuantity,
                                @NotNull @Size(max = 255) @NotEmpty String imageUrl,
                                List<String> categories) implements Serializable {
}