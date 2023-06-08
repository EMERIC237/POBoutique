package com.pembo.store.model_test_from_emeric;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String region;


    // default constructor
    protected Address() {
    }


    public Address(String street, String city, String region) {
        this.street = street;
        this.city = city;
        this.region = region;
    }

    

}
