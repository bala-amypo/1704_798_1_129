package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CREATE
    public Product create(Product product) {
        return productRepository.save(product);
    }

    // UPDATE
    public Product update(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setSku(product.getSku());
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setActive(product.getActive());

        return productRepository.save(existing);
    }

    // GET BY ID
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // GET ALL
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // DEACTIVATE
    public void deactivate(Long id) {
        Product product = getById(id);
        product.setActive(false);
        productRepository.save(product);
    }
}
