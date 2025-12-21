package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CREATE
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // UPDATE
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    // READ BY ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // READ ALL
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // DELETE / DEACTIVATE
    public void deactivateProduct(Long id) {
        productRepository.deleteById(id);
    }
}
