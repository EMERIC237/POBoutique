package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.pembo.store.model.ProductCategory}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public record ProductCategoryDto(Long id, @NotNull CategoryDto category) implements Serializable {
}