package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Long id;
    private Long userId;
    private boolean active = true;
    private List<CartItem> items = new ArrayList<>();

    public Cart() {
    }

    public Cart(Long userId) {
        this.userId = userId;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // 🔥 THESE TWO METHODS ARE MANDATORY
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
