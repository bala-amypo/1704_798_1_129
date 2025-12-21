package com.example.demo.service.impl;

import com.example.demo.entity.DiscountApplication;
import com.example.demo.repository.DiscountApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl {

    private final DiscountApplicationRepository repository;

    public DiscountServiceImpl(DiscountApplicationRepository repository) {
        this.repository = repository;
    }

    public DiscountApplication save(DiscountApplication discount) {
        return repository.save(discount);
    }

    public List<DiscountApplication> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
