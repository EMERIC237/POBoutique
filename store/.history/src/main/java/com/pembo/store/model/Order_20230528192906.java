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

    // default constructor
    protected Order() {
    }

}
