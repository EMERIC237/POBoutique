package com.pembo.store.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.pembo.store.model.Category}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public record CategoryDto(Long id,
                          @NotNull @Size(max = 255) @NotEmpty(message = "Category name cannot be empty") String name) implements Serializable {
}