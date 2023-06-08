package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.pembo.store.model.Order}
 */
@Value
public class OrderDto implements Serializable {
    Long id;
    String userUsername;
    @NotNull
    AddressDto address;
    @NotNull
    LocalDate dateCreated;
    @Size(max = 255)
    String status;
    @NotNull(message = "Order must have at least one item")
    Set<OrderItemDto> orderItems;
}