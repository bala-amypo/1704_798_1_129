package com.example.demo.controller;

import com.example.demo.entity.DiscountApplication;
import com.example.demo.service.impl.DiscountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountServiceImpl discountService;

    public DiscountController(DiscountServiceImpl discountService) {
        this.discountService = discountService;
    }

    @PostMapping
    public DiscountApplication create(@RequestBody DiscountApplication discount) {
        return discountService.save(discount);
    }

    @GetMapping
    public List<DiscountApplication> getAll() {
        return discountService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        discountService.deleteById(id);
    }
}
