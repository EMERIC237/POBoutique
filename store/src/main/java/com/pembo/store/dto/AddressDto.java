package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.pembo.store.model.Address}
 */
public record AddressDto(Long id, Long userId, @NotNull @Size(max = 255) String street,
                         @NotNull @Size(max = 255) String city,
                         @Size(max = 255) String region) implements Serializable {
}
