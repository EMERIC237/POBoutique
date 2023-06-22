package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.pembo.store.model.Review}
 */
public record ReviewDto(@NotNull Long id,
                        @NotNull @Positive @Range(message = "rating must be between 1 and 5", min = 1, max = 5) Integer rating,
                        @NotNull LocalDate date, String comment) implements Serializable {
}