package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class BundleRule {

    @Id
    @GeneratedValue
    private Long id;

    private String productIdsCsv;
    private int discountPercentage;
    private boolean active;
}
