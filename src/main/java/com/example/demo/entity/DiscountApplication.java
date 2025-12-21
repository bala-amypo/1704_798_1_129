package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class DiscountApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cartId;
    private String description;
    private BigDecimal discountAmount;

    public Long getId() {
        return id;
    }

    public Long getCartId() {
        return cartId;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
}
