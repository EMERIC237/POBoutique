package com.pembo.store.controller;

import com.pembo.store.dto.CartItemDto;
import com.pembo.store.dto.CartItemRequestDto;
import com.pembo.store.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{userId}/cart/items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/{productId}")
    public CartItemDto createCartItem(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        return cartItemService.createCartItem(userId, productId, quantity);
    }

    @PostMapping
    public List<CartItemDto> createCartItems(@PathVariable Long userId, @RequestBody List<CartItemRequestDto> items) {
        return cartItemService.createCartItems(userId, items);
    }


    @PutMapping("/{itemId}")
    public CartItemDto updateCartItem(@PathVariable Long userId, @PathVariable Long itemId, @RequestParam int quantity) {
        return cartItemService.updateCartItem(userId, itemId, quantity);
    }

    @DeleteMapping("/{itemId}")
    public void deleteCartItem(@PathVariable Long userId, @PathVariable Long itemId) {
        cartItemService.deleteCartItem(userId, itemId);
    }
}
