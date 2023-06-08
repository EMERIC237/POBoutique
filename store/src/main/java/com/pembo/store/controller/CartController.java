package com.pembo.store.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.pembo.store.dto.CartDto;
import com.pembo.store.service.CartService;

@RestController
@RequestMapping("api/v1/carts")
public class CartController {
    
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartDto> getAllCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public CartDto getCartById(@PathVariable Long id){
        return cartService.getCartById(id);
    }

    @PostMapping
    public CartDto createCart(@RequestBody CartDto cartDto){
        return cartService.saveCart(cartDto);
    }

    @PutMapping("/{id}")
    public CartDto updateCart(@PathVariable Long id, @RequestBody CartDto cartDto){
        return cartService.updateCart(id, cartDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
    }
}
