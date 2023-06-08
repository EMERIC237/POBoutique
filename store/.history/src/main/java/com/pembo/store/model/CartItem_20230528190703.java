package com.pembo.store.model_test_from_emeric;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    private it quantity;

    // default constructor
    protected CartItem() {
    }

    public CartItem(Integer quantity) {
        this.quantity = quantity;
    }


    
}
