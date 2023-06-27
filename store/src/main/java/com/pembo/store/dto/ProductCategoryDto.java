package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;


import java.io.Serializable;

/**
 * DTO for {@link com.pembo.store.model.ProductCategory}
 */

public record ProductCategoryDto(Long id,@NotNull ProductDto product, @NotNull CategoryDto category) implements Serializable {
}