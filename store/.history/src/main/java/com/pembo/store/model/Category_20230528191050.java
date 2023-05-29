package com.pembo.store.model;

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
    protected Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
