package com.pembo.store.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    private int quantity;

    // default constructor
    protected CartItem() {
    }

    public CartItem(int quantity) {
        this.quantity = quantity;
    }

    


    
}
