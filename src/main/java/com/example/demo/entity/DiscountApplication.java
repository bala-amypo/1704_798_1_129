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

    @PreUpdate
    void blockUpdate() {
        throw new UnsupportedOperationException();
    }
}
