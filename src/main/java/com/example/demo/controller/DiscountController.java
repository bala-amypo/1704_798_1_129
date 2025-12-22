package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    @PostMapping("/evaluate/{cartId}")
    public String evaluateDiscounts(@PathVariable Long cartId) {
        return "Evaluate discounts for cart " + cartId;
    }

    @GetMapping("/{id}")
    public String getDiscount(@PathVariable Long id) {
        return "Get discount application " + id;
    }

    @GetMapping("/cart/{cartId}")
    public String getDiscountsForCart(@PathVariable Long cartId) {
        return "Get discounts for cart " + cartId;
    }
}
