package com.example.task1_loginapp_feature1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task1_loginapp_feature1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // The primary key is now email, so we extend JpaRepository<User, String>
    
    // We don't need findByEmail since findById will work with the email
    // But we can add other useful methods
    
    Optional<User> findByMobile(String mobile);
    
    // For login convenience
    Optional<User> findByEmailAndPassword(String email, String password);
}