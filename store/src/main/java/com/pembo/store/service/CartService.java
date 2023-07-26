package com.pembo.store.service;

import java.util.List;
import java.util.stream.Collectors;

import com.pembo.store.dto.CartDto;
import com.pembo.store.dto.UserDto;
import com.pembo.store.mapper.CartMapper;
import com.pembo.store.mapper.UserMapper;
import com.pembo.store.model.Cart;
import com.pembo.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper, UserService userService,
                       UserMapper userMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * get all carts
     *
     * @return List of all carts
     */
    public List<CartDto> getAllUserCarts(Long userId) {
        return cartRepository.findByUser(userId)
                             .stream()
                             .map(cartMapper::toDto)
                             .collect(Collectors.toList());
    }

    /**
     * get cart by id
     *
     * @param id
     * @return cart
     */
    public CartDto getCartById(,Long id) {
        return cartMapper.toDto(cartRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("Cart Not found")));

    }

    /**
     * save cart
     *
     * @param userId
     * @return saved cart
     */
    public CartDto saveCart(Long userId) {
        UserDto userDto = userService.getUserById(userId);
        Cart cart = new Cart();
        cart.setUser(userMapper.toEntity(userDto));
        return cartMapper.toDto(cartRepository.save(cart));
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
