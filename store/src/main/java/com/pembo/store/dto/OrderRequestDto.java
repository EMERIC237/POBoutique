package com.pembo.store.dto;

import com.pembo.store.validation.ValidOrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public record OrderRequestDto(Long id, @NotNull Long addressId, @NotNull LocalDate dateCreated,
                              @ValidOrderStatus String status,
                              @NotNull(message = "Order must have at least one item") Set<OrderItemRequestDto> orderItems) implements Serializable {
}

