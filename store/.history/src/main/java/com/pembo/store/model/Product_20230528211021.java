package com.pembo.store.model;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "image_url")
    private String imageUrl;

    // RELATIONSHIPS
    @OneToMany(mappedBy = "product")
    private List<ProductCategory> productCategories = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // default constructor
    protected Product() {
    }

    public Product(String name, String description, BigDecimal price, int stockQuantity, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }

    // add prouct to product_category
    public void addProductCategory(ProductCategory productCategory){
        productCategories.add(productCategory);
        productCategory.setProduct(this);
    }

    // remove product from product_category
    public void removeProductCategory(ProductCategory productCategory){
        productCategories.remove(productCategory);
        productCategory.setProduct(null);
    }

    // add product to cart_item
    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
        cartItem.setProduct(this);
    }

    // remove product from cart_item
    public void removeCartItem(CartItem cartItem){
        cartItems.remove(cartItem);
        cartItem.setProduct(null);
    }
    // add a product's review
    public void addReview(Review review){
        reviews.add(review);
        review.setProduct(this);
    }

    // remove a product's review
    public void removeReview(Review review){
        reviews.remove(review);
        review.setProduct(null);
    }

    // add a product t order item
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setProduct(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
