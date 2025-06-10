package com.example.task1_loginapp_feature1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task1_loginapp_feature1.model.User;
import com.example.task1_loginapp_feature1.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public String signUp(User user) {
        // Check if a user with the given email already exists
        if(userRepository.existsById(user.getEmail())) {
            return "User Already Exists!";
        }
        userRepository.save(user);
        return "User Signed Up Successfully!";
    }
    
    public Optional<User> login(String email, String password) {
        Optional<User> userOptional = userRepository.findById(email);
        if(userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return userOptional; // Return the user object if login successful
        }
        return Optional.empty(); // Return empty if login failed
    }
}