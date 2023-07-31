package com.pembo.store.repository;

import com.pembo.store.model.Review;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser_Id(Long userId);

    List<Review> findByProduct_Id(Long productId);
}