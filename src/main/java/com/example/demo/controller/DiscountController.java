package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    
    @Autowired
    private DiscountService discountService;
    
    @PostMapping("/evaluate/{cartId}")
    public ResponseEntity<List<DiscountApplication>> evaluateDiscounts(@PathVariable Long cartId) {
        List<DiscountApplication> discounts = discountService.evaluateDiscounts(cartId);
        return ResponseEntity.ok(discounts);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DiscountApplication> getDiscountApplication(@PathVariable Long id) {
        DiscountApplication discount = discountService.getDiscountById(id);
        return ResponseEntity.ok(discount);
    }
    
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<DiscountApplication>> getDiscountsForCart(@PathVariable Long cartId) {
        List<DiscountApplication> discounts = discountService.getDiscountsForCart(cartId);
        return ResponseEntity.ok(discounts);
    }
}