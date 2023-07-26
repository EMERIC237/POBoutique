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
    public List<CartDto> getAllUserCarts(@PathVariable Long userId) {
        return cartService.getAllUserCarts(userId);
    }

    @GetMapping("/{id}")
    public CartDto getUserCartById(@PathVariable Long userId, @PathVariable Long id) {
        return cartService.getCartById(userId, id);
    }

    @PostMapping
    public CartDto createUserCart(@PathVariable Long userId) {
        return cartService.saveCart(userId);
    }

    @PutMapping("/{id}")
    public CartDto updateUserCart(@PathVariable Long id, @RequestBody CartDto cartDto) {
        return cartService.updateCart(id, cartDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUserCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
