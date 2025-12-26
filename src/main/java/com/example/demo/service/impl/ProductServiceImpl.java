package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    private void validatePrice(BigDecimal price) {
        if (price != null && price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
    }
    
    public Product createProduct(Product product) {
        validatePrice(product.getPrice());
        
        Optional<Product> existing = productRepository.findBySku(product.getSku());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("SKU already exists");
        }
        
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product product) {
        validatePrice(product.getPrice());
        
        Product existing = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        
        // Check for SKU duplication if SKU is being changed
        if (product.getSku() != null && !product.getSku().equals(existing.getSku())) {
            Optional<Product> duplicateSku = productRepository.findBySku(product.getSku());
            if (duplicateSku.isPresent()) {
                throw new IllegalArgumentException("SKU already exists");
            }
        }
        
        if (product.getName() != null) {
            existing.setName(product.getName());
        }
        if (product.getPrice() != null) {
            existing.setPrice(product.getPrice());
        }
        if (product.getCategory() != null) {
            existing.setCategory(product.getCategory());
        }
        if (product.getSku() != null) {
            existing.setSku(product.getSku());
        }
        
        return productRepository.save(existing);
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public void deactivateProduct(Long id) {
        Product product = getProductById(id);
        product.setActive(false);
        productRepository.save(product);
    }
}