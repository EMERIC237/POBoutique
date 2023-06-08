package com.pembo.store.model_test_from_emeric;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "date_created")
    private Date orderDate;

    private String status;

    // RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "user_id")

    // default constructor
    protected Order() {
    }

    public Order(Date orderDate, String status) {
        this.orderDate = orderDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    

}
