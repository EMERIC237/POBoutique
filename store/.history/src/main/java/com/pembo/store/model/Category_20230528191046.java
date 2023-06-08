package com.pembo.store.model_test_from_emeric;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    // default constructor
}
