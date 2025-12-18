package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bundle_rules")
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productIdsCsv; // "1,2,3"

    private int discountPercentage;
    private boolean active = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductIdsCsv() { return productIdsCsv; }
    public void setProductIdsCsv(String productIdsCsv) {
        this.productIdsCsv = productIdsCsv;
    }

    public int getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
             