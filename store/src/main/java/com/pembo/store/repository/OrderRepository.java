package com.pembo.store.repository;

import com.pembo.store.model.Order;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

}