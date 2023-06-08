package com.pembo.store.service;

import java.util.List;
import java.util.stream.Collectors;

import com.pembo.store.dto.CartDto;
import com.pembo.store.mapper.CartMapper;
import com.pembo.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    /**
     * get all carts
     * 
     * @return List of all carts
     */
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream().map(cartMapper::toDto).collect(Collectors.toList());
    }

    /**
     * get cart by id
     * 
     * @param id
     * @return cart
     */
    public CartDto getCartById(Long id){
        return cartMapper.toDto(cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart Not found")));

    }

    /**
     * save cart
     * 
     * @param cart
     * @return saved cart
     */
    public CartDto saveCart(CartDto cart) {
        return cartMapper.toDto(cartRepository.save(cartMapper.toEntity(cart)));
    }

    /**
     * update cart
     * 
     * @param id
     * @param cart
     * @return updated cart
     */
    public CartDto updateCart(Long id, CartDto cart) {
        return cartRepository.findById(id)
                .map(cartToUpdate -> {
                    cartMapper.partialUpdate(cart, cartToUpdate);
                    return cartMapper.toDto(cartRepository.save(cartToUpdate));
                })
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    /**
     * delete cart
     * 
     * @param id
     */
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }


}
