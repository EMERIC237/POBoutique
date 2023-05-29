package com.pembo.store.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    // default constructor
    protected Cart() {
    }

    // add a cart item to the cart
    public void addCardItem(CartItem cartItem){
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    // remove a cart item from the cart
    public void removeCartItem(CartItem cartItem){
        cartItems.remove(cartItem);
        cartItem.setCart(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

}
