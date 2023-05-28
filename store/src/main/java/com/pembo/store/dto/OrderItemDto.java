package com.pembo.store.dto;
/*Should I create a ProductItemDto abstract class and then extend it ?
public class ProductItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    private String imageUrl;

    // constructors, getters, and setters...
}
*/

import java.math.BigDecimal;

public class OrderItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    // price at the time of the order
    private BigDecimal priceEach;
    private String imageUrl;

    public OrderItemDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
