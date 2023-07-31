package com.pembo.store.service;


import com.pembo.store.dto.CartDto;
import com.pembo.store.exception.ResourceNotFoundException;
import com.pembo.store.mapper.CartMapper;
import com.pembo.store.model.Cart;
import com.pembo.store.model.User;
import com.pembo.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    private final UserService userService;

    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper, UserService userService) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userService = userService;
    }

    public CartDto getUserCart(Long userId) {
        return cartMapper.toDto(findUserCartById(userId));
    }

    public CartDto saveCart(Long userId) {
        User user = userService.findUserById(userId);
        Cart cart = new Cart();
        cart.setUser(user);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    public CartDto updateCart(Long userId, CartDto cartDto) {
        Cart cart = findUserCartById(userId);
        cartMapper.partialUpdate(cartDto, cart);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    public void deleteCart(Long userId) {
        cartRepository.delete(findUserCartById(userId));
    }

    public Cart findUserCartById(Long userId) {
        User user = userService.findUserById(userId);
        return cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart for user with id " +
                userId + "Not Found"));
    }

}
