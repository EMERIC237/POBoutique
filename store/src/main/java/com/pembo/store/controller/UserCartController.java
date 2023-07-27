package com.pembo.store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.pembo.store.dto.CartDto;
import com.pembo.store.service.CartService;

@RestController
@RequestMapping("api/v1/users/{userId}/cart")
public class UserCartController {
    private final CartService cartService;

    public UserCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartDto getUserCart(@PathVariable Long userId) {
        return cartService.getUserCart(userId);
    }

    @PostMapping
    public CartDto createUserCart(@PathVariable Long userId) {
        return cartService.saveCart(userId);
    }

    @PutMapping
    public CartDto updateUserCart(@PathVariable Long userId, @RequestBody CartDto cartDto) {
        return cartService.updateCart(userId, cartDto);
    }

    @DeleteMapping
    public void deleteUserCart(@PathVariable Long userId) {
        cartService.deleteCart(userId);
    }
}
