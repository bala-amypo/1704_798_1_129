package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product create(Product product) {
        return product;
    }

    @Override
    public Product update(Long id, Product product) {
        return product;
    }

    @Override
    public Product getById(Long id) {
        return new Product();
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>();
    }

    @Override
    public void deactivate(Long id) {
        // nothing needed
    }
}
