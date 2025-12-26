package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(User user) {
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User updateUser(Long id, User user) {
        User existing = getUserById(id);
        
        if (user.getEmail() != null && !user.getEmail().equals(existing.getEmail())) {
            Optional<User> duplicateEmail = userRepository.findByEmail(user.getEmail());
            if (duplicateEmail.isPresent()) {
                throw new IllegalArgumentException("Email already exists");
            }
            existing.setEmail(user.getEmail());
        }
        
        if (user.getPassword() != null) {
            existing.setPassword(user.getPassword());
        }
        if (user.getRole() != null) {
            existing.setRole(user.getRole());
        }
        
        return userRepository.save(existing);
    }
    
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}