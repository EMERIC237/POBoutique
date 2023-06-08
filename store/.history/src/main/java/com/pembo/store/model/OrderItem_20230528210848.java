package com.pembo.store.model_test_from_emeric;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    private int quantity;

    @Column(name = "price_title")
    private int orderPrice;

    // RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Many

    // default constructor
    protected OrderItem() {
    }

    public OrderItem(int quantity, int orderPrice) {
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
