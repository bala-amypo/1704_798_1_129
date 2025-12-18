package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
}
