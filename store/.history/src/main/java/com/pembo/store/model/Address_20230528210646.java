package com.pembo.store.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    private String street;
    private String city;
    private String region;

    // RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "address")
    private List<Order> orders = new ArrayList<>();

    // default constructor
    protected Address() {
    }


    public Address(String street, String city, String region) {
        this.street = street;
        this.city = city;
        this.region = region;
    }

    // add an order to the address
    public void addOrder(Order order) {
        orders.add(order);
        order.setAddress(this);
    }

    // remove an order from the address
    // Not sure if this is needed !!!
    public void removeOrder(Order order) {
        orders.remove(order);
        order.setAddress(null);
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getRegion() {
        return region;
    }


    public void setRegion(String region) {
        this.region = region;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }



    

}
