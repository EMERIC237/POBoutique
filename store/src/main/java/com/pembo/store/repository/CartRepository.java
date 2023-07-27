package com.pembo.store.repository;

import com.pembo.store.model.Cart;
import com.pembo.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);
}