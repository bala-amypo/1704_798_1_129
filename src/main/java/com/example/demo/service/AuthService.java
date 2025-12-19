package com.example.demo.service;

public interface AuthService {

    String register(String email, String password, String role);

    String login(String email, String password);
}
