package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "discount_applications")
public class DiscountApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cartId;
    private Long bundleRuleId;

    private BigDecimal discountAmount;
    private BigDecimal finalPrice;

    private LocalDateTime appliedAt = LocalDateTime.now();

    @PreUpdate
    public void preventUpdate() {
        throw new UnsupportedOperationException("Immutable record");
    }

    public Long getId() { return id; }
    public Long getCartId() { return cartId; }
    public Long getBundleRuleId() { return bundleRuleId; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public BigDecimal getFinalPrice() { return finalPrice; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
}
