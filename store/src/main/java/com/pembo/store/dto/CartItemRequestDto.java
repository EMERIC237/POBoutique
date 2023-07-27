package com.pembo.store.dto;

import jakarta.validation.constraints.NotNull;

public record CartItemRequestDto(@NotNull Long productId, @NotNull int quantity) {

}

