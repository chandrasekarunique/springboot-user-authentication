package com.example.task1_loginapp_feature1.controller;

import java.util.Map;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task1_loginapp_feature1.model.User;
import com.example.task1_loginapp_feature1.repository.UserRepository;
import com.example.task1_loginapp_feature1.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, Object> request) {
        try {
            String email = (String) request.get("email");
            String password = (String) request.get("password");
            
            if (email == null || password == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Email and password are required");
                response.put("success", false);
                return ResponseEntity.badRequest().body(response);
            }

            boolean isSignup = false;
            if (request.containsKey("isSignup")) {
                // Handle different types that might come from form data
                Object isSignupObj = request.get("isSignup");
                if (isSignupObj instanceof Boolean) {
                    isSignup = (Boolean) isSignupObj;
                } else if (isSignupObj instanceof String) {
                    isSignup = Boolean.parseBoolean((String) isSignupObj);
                }
            }

            if (isSignup) {
                // Get additional signup fields
                String firstName = (String) request.get("firstName");
                String lastName = (String) request.get("lastName");
                String mobile = (String) request.get("mobile");
                String gender = (String) request.get("gender");
                String location = (String) request.get("location");
                
                if (firstName == null || lastName == null || mobile == null || gender == null || location == null) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "All fields are required for signup");
                    response.put("success", false);
                    return ResponseEntity.badRequest().body(response);
                }
                
                if (userRepository.existsById(email)) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "User already exists! Please log in.");
                    response.put("success", false);
                    return ResponseEntity.badRequest().body(response);
                }
                
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setMobile(mobile);
                newUser.setGender(gender);
                newUser.setLocation(location);
                
                // Set default date of birth
                Date dob = new Date();
                newUser.setDob(dob);
                
                userRepository.save(newUser);
                
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Signup successful! You can now log in.");
                response.put("success", true);
                return ResponseEntity.ok(response);
            } else {
                // Login
                Optional<User> userOptional = userService.login(email, password);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Login Successful! Welcome, " + user.getFullName() + "!");
                    response.put("userName", user.getFullName());
                    response.put("success", true);
                    return ResponseEntity.ok(response);
                } else {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Invalid email or password!");
                    response.put("success", false);
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                }
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error processing request: " + e.getMessage());
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}