# Geographical API 🌍

A Spring Boot backend application that provides REST APIs for managing geographical data.

## 🚀 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Gradel
- REST APIs
- Global Exception Handling
- Caching using @Cacheable

---

## 📌 Features Implemented

- Layered Architecture (Controller → Service → Repository)
- DTO pattern for secure data transfer
- Global Exception Handling using @ControllerAdvice
- Proper HTTP status codes (404, 200, etc.)
- Caching to optimize API performance
- Clean project structure

---

## 📡 Sample API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/geographical/{id} | Get geographical data by ID |
| POST | /api/geographical | Create new record |
| PUT | /api/geographical/{id} | Update record |
| DELETE | /api/geographical/{id} | Delete record |

---
