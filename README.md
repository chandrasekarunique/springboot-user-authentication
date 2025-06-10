# springboot-user-authentication - Spring Boot Login & Signup Application

This is a backend Spring Boot application that implements **User Signup and Login functionalities** with secure API endpoints. It uses **MySQL** as the database and **Spring Security**, **Spring Data JPA**, and **REST APIs** for authentication and data persistence.

---

## Features

- ✅ Signup with full user details (email, password, name, mobile, gender, location, DOB)
- ✅ Login with existing credentials
- ✅ REST API support for both operations
- ✅ MySQL integration for data storage
- ✅ CORS configuration for frontend integration
- ✅ Spring Security for basic endpoint protection
- ✅ DTO-friendly and API-structured response format

---

## Tech Stack

| Layer         | Technology Used                      |
|---------------|---------------------------------------|
| Backend       | Java, Spring Boot                    |
| Security      | Spring Security                      |
| Database      | MySQL                                |
| ORM           | Spring Data JPA, Hibernate           |
| Build Tool    | Maven                                |
| API Format    | REST API (JSON)                      |

---

## 📁 Project Structure

```text
src/main/java/
├── com.example.task1_loginapp_feature1
│   ├── Task1LoginappFeature1Application.java
│   ├── config
│   │   ├── CorsConfig.java
│   │   └── SecurityConfig.java
│   ├── controller
│   │   └── UserController.java
│   ├── model
│   │   └── User.java
│   ├── repository
│   │   └── UserRepository.java
│   └── service
│       └── UserService.java

