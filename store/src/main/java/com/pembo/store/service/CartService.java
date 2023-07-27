package com.pembo.store.service;

import java.util.List;
import java.util.stream.Collectors;

import com.pembo.store.dto.CartDto;
import com.pembo.store.dto.UserDto;
import com.pembo.store.exception.ResourceNotFoundException;
import com.pembo.store.mapper.CartMapper;
import com.pembo.store.mapper.UserMapper;
import com.pembo.store.model.Cart;
import com.pembo.store.model.User;
import com.pembo.store.repository.CartRepository;
import com.pembo.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    private final UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userRepository = userRepository;
    }

    public CartDto getUserCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return cartMapper.toDto(cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart not found")));
    }

    public CartDto saveCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Cart cart = new Cart();
        cart.setUser(user);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    public CartDto updateCart(Long userId, CartDto cartDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cartMapper.partialUpdate(cartDto, cart);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    public void deleteCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cartRepository.delete(cart);
    }

}
