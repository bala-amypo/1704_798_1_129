package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @Valid @RequestBody Product product) {
        return productService.update(id, product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping
    public List<Product> listProducts() {
        return productService.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateProduct(@PathVariable Long id) {
        productService.deactivate(id);
    }
}
