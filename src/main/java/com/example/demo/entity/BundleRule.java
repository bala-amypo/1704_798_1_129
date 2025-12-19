package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class BundleRule {

    @Id
    @GeneratedValue
    private Long id;

    private String productIds;
    private double discountPercentage;
    private boolean active;

    public Long getId() { return id; }
    public double getDiscountPercentage() { return discountPercentage; }
}
