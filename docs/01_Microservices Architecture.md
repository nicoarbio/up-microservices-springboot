# Microservices Architecture – Iteration 1

This document defines the technical architecture for the first iteration of the dealership system. It includes the microservices to be implemented, their responsibilities, data ownership, and how they interact through Eureka and Feign.

**Target**: ABMs working, services discoverable via Eureka, functional inter-service communication with Feign, and Postman collection ready.

---

## 📦 Shared Configuration

All services will:
- Register with `eureka-server`
- Use **Spring Boot + Spring Web + Spring Data JPA + H2**
- Expose REST APIs for CRUD operations
- Use Feign clients for inter-service calls
- Initialize with embedded H2 and startup data (initialization using data in JSON file)
- All projects will share the base package: `com.nicoarbio.cardealership`
- All projects will have the `Spring Web`, `Spring Boot DevTools` and `Spring Boot Actuator` Maven Dependencies.

---

## 🌐 Service Discovery

### ✅ `eureka-server`
- **Maven Artifact**: `eureka-server`
- **Role**: Service registry for all microservices.
- **Technology**: Spring Cloud Netflix Eureka.
- **Port**: `8761`
- **Database**: None
- **Notes**: All other services will register as Eureka clients.
- **Maven Dependencies**:
  - Spring Cloud Eureka Server

---

## 🧩 Core Microservices

### 🏢 `branch-service`
- **Maven Artifact**: `branch-service`
- **Responsibility**: Manage dealership branches.
- **Entities**: `Branch`
- **Port**: `8081`
- **Database**: H2
- **Maven Dependencies**:
  - Spring Validation
  - Spring Data JPA
  - H2 Database
  - Eureka Discovery Client

---

### 🚗 `vehicle-model-service`
- **Maven Artifact**: `vehicle-model-service`
- **Responsibility**: Manage `VehicleModel` (catalog of car types).
- **Entities**: `VehicleModel`
- **Port**: `8082`
- **Database**: H2 with `data.sql` on startup
- **Dependencies**: None
- **Maven Dependencies**:
  - Spring Validation
  - Spring Data JPA
  - H2 Database
  - Eureka Discovery Client

---

### 📦 `vehicle-unit-service`
- **Maven Artifact**: `vehicle-unit-service`
- **Responsibility**: Manage physical cars (`VehicleUnit`) including licensePlate, location, and status.
- **Entities**: `VehicleUnit`
- **Port**: `8083`
- **Database**: H2 with `data.sql` on startup
- **Dependencies**:
  - Calls `vehicle-model-service` via Feign to get `VehicleModel` details
- **Maven Dependencies**:
  - Spring Validation
  - Spring Data JPA
  - H2 Database
  - Eureka Discovery Client
  - OpenFeign

---

### 👤 `customer-service`
- **Maven Artifact**: `customer-service`
- **Responsibility**: Manage customers.
- **Entities**: `Customer`
- **Port**: `8084`
- **Database**: H2
- **Maven Dependencies:**
  - Spring Validation
  - Spring Data JPA
  - H2 Database
  - Eureka Discovery Client

---

### 💰 `sales-service`
- **Maven Artifact**: `sales-service`
- **Responsibility**: Register and query sales. Links customers and vehicles.
- **Entities**: `Sale`
- **Port**: `8085`
- **Database**: H2
- **Dependencies**:
  - Calls `customer-service` to validate customer
  - Calls `vehicle-unit-service` to mark vehicle as SOLD
  - Calls `branch-service` to validate branch
- **Maven Dependencies:**
  - Spring Validation
  - Spring Data JPA
  - H2 Database
  - Eureka Discovery Client
  - OpenFeign

---

### 🛠️ `maintenance-service`
- **Maven Artifact**: `maintenance-service`
- **Responsibility**: Manage maintenance services, whether the vehicle was sold or not.
- **Entities**: `MaintenanceService`
- **Port**: `8086`
- **Database**: H2
- **Dependencies**:
  - Calls `vehicle-unit-service` to get `VehicleUnit`
  - Calls `customer-service` to get `Customer`
  - Calls `sales-service` to determine warranty eligibility
- **Maven Dependencies:**
  - Spring Validation
  - Spring Data JPA
  - H2 Database
  - Eureka Discovery Client
  - OpenFeign
