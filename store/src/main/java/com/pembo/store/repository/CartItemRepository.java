package com.pembo.store.repository;

import com.pembo.store.model.Cart;
import com.pembo.store.model.CartItem;
import com.pembo.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
