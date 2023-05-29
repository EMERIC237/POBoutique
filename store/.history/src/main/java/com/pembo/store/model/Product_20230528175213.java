package com.pembo.store.model;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private int quantity;

    @Column(name = "image_url")
    private String imageUrl;

}