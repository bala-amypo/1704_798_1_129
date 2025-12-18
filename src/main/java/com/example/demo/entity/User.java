package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
}
u88887uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuujhn87 n