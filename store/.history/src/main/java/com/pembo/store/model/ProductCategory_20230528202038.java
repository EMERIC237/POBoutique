package com.pembo.store.model_test_from_emeric;

import jakarta.persistence.*;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Long id;

    // RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // default constructor
    protected ProductCategory() {
    }

    

    public Product getProduct() {
        return product;
    }



    public void setProduct(Product product) {
        this.product = product;
    }



    public Category getCategory() {
        return category;
    }



    public void setCategory(Category category) {
        this.category = category;
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    
    
}
