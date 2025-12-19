package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class DiscountApplication {

    @Id
    @GeneratedValue
    private Long id;

    private Long cartId;
    private Long bundleRuleId;
    private BigDecimal discountAmount;
    private BigDecimal finalPrice;

    // ✅ ADD THIS METHOD (ANYWHERE INSIDE THE CLASS)
    public void setBundleRuleId(Long bundleRuleId) {
        this.bundleRuleId = bundleRuleId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    @PreUpdate
    void blockUpdate() {
        throw new UnsupportedOperationException();
    }
}
