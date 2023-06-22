package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link com.pembo.store.model.Product}
 */
public record ProductResponseDto(@NotNull Long id, @NotNull @Size(max = 255) String name,
                                 @Size(max = 255) String description, @NotNull BigDecimal price,
                                 @NotNull Integer stockQuantity, @NotNull @Size(max = 255) String imageUrl,
                                 List<CategoryDto> categories, int reviewsCount,
                                 Double averageReviewRating) implements Serializable {
}