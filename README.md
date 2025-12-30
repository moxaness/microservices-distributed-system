# 🚀 Microservices Distributed System

A **production-grade, cloud-native microservices architecture** built with **Java 17**, **Spring Boot**, and **Spring Cloud**.  
This project showcases real-world backend engineering practices including service discovery, API gateway security, asynchronous messaging, and containerized deployments ready for Kubernetes.

---

## 🧭 Project Overview

This system is composed of multiple independently deployable services that communicate synchronously via REST and asynchronously via messaging.  
It is designed with scalability, resilience, and clean separation of concerns in mind.

### Core Services
- **API Gateway** – Central entry point, request routing, and security enforcement
- **Eureka Server** – Service registry and discovery
- **Customer Service** – Handles customer registration and persistence
- **Fraud Service** – Performs fraud checks on newly registered customers
- **Notification Service** – Sends notifications asynchronously via RabbitMQ

---

## 🛠️ Tech Stack

- **Language:** Java 17
- **Frameworks:** Spring Boot, Spring Cloud
    - Spring Cloud Gateway
    - Eureka Server & Client
    - OpenFeign
- **Database:** PostgreSQL
- **Messaging:** RabbitMQ
- **Containerization:** Docker & Docker Compose
- **Orchestration Ready:** Kubernetes (Minikube)
- **Build Tool:** Maven

---

## ✨ Key Features

- 🔐 **API Gateway Security**
    - Custom `GlobalFilter` for API Key authorization
    - Uses `Ordered.HIGHEST_PRECEDENCE` to enforce security before routing

- 🔄 **Asynchronous Communication**
    - Customer Service publishes events to RabbitMQ
    - Notification Service consumes and processes messages independently

- 🌐 **Service Discovery**
    - Eureka-based discovery for dynamic service registration
    - Configured with `prefer-ip-address: true` to avoid macOS `.local` DNS resolution issues

- 📦 **Containerized Architecture**
    - Fully containerized using Docker Compose
    - Kubernetes-ready deployments tested with Minikube

---

## 🧱 System Architecture (Text Description)

1. External clients send requests to the **API Gateway**
2. The **API Gateway**:
    - Validates requests using an API Key
    - Routes traffic to downstream services via service discovery
3. **Customer Service**:
    - Persists customer data in PostgreSQL
    - Calls **Fraud Service** synchronously using OpenFeign
    - Publishes a notification event to RabbitMQ
4. **Fraud Service**:
    - Performs fraud validation logic
    - Communicates results back to Customer Service
5. **Notification Service**:
    - Consumes messages from RabbitMQ
    - Processes notifications asynchronously
6. **Eureka Server**:
    - Maintains service registry using IP-based registration
    - Enables dynamic service-to-service communication

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Docker & Docker Compose
- Maven

---

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/microservices-distributed-system.git
cd microservices-distributed-system
## 2️⃣ Build the Project
```bash
mvn clean install
```

## 3️⃣ Start Infrastructure with Docker Compose
```bash
docker-compose up -d
```

This will start the following infrastructure components:

- **PostgreSQL** – Persistent storage for services
- **RabbitMQ** – Asynchronous message broker
- Supporting infrastructure services required by the system

## 4️⃣ Start Services (Recommended Order)

Start the Spring Boot services in the following order to ensure proper service discovery and routing:

1. **Eureka Server** – Port: `8761`
2. **API Gateway** – Port: `8083`
3. **Customer Service** – Port: `8080`
4. **Fraud Service** – Port: `8081`
5. **Notification Service** – Port: `8082`

---

## 🧪 Testing the API

### Example: Create a Customer (via API Gateway)

**Request**
```http
POST http://localhost:8083/api/v1/customers
```

**Headers**
```http
ApiKey: supersecure
Content-Type: application/json
```

**Body**
```json
{
  "firstName": "Anes",
  "lastName": "Code",
  "email": "anescode@gmail.com"
}
```

**Expected Result**

- ✅ `200 OK`
- 🗄️ Customer persisted in PostgreSQL
- 🔍 Fraud check executed
- 📩 Notification event published to RabbitMQ

---

## 🧠 Challenges Overcome

### 🐘 MacOS `.local` DNS Resolution Issue

**Problem**

Eureka clients on macOS attempted to register using `.local` hostnames, which caused service resolution failures in Docker and Kubernetes environments.

**Solution**

Configured all services to prefer IP-based registration:
```yaml
eureka:
  instance:
    prefer-ip-address: true
```

**Result**

- IP-based service registration
- Reliable inter-service communication
- Seamless compatibility across Docker, Minikube, and local environments

---

## 📦 Deployment Notes

- Images are built using Maven (Jib / Buildpacks)
- Docker Compose is used for local development
- Kubernetes manifests are compatible with Minikube
- No environment-specific code changes are required

---

## ✅ Project Status

- ✔ Fully functional
- ✔ Dockerized
- ✔ Kubernetes-ready
- ✔ Production-style architecture

Built with a strong focus on real-world backend engineering practices, scalability, and reliability.

Feel free to explore, extend, and adapt this system for your own distributed architectures 🚀