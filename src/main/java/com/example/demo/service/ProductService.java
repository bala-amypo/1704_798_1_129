package com.example.demo.service;

import com.example.demo.entity.Product;
import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product update(Long id, Product product);

    Product getById(Long id);

    List<Product> getAll();

    void deactivate(Long id);
}
