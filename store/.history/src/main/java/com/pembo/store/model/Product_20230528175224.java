package com.pembo.store.model_test_from_emeric;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String name;
    private String description;

    @Column
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private int quantity;

    @Column(name = "image_url")
    private String imageUrl;

}
