# Microservices Project

A Java/Spring Boot project for building a service-oriented backend around product and cart functionality.

The repository is currently centered on a working **Product Service**. It provides RESTful CRUD operations backed by Spring Data JPA and an in-memory H2 database. The project also includes dependencies for Kafka and reactive HTTP communication as the application evolves toward multiple communicating services.

## Current Implementation

### Product Service

The Product Service currently supports:

- Create a product
- Retrieve all products
- Retrieve a product by ID
- Update a product
- Delete a product
- Persistence through Spring Data JPA
- Local development with an H2 in-memory database

## Tech Stack

- Java 17
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- Maven
- Lombok

### Dependencies prepared for expansion

The build also includes:

- Spring Kafka
- Spring WebFlux / WebClient
- Microsoft SQL Server JDBC driver

These dependencies provide a foundation for future event-driven communication, service-to-service calls, and migration from the local H2 database to SQL Server.

## Project Structure

```text
Microservices-Project/
└── product-service/
    ├── src/main/java/com/microservices/product_service/
    │   ├── controller/
    │   ├── entity/
    │   ├── repository/
    │   └── service/
    ├── src/main/resources/
    │   └── application.properties
    └── pom.xml
```

## Product API

The Product Service runs locally on port `8081`.

Base URL:

```text
http://localhost:8081/products
```

| Method | Endpoint | Purpose |
| --- | --- | --- |
| POST | `/products` | Create a product |
| GET | `/products` | Get all products |
| GET | `/products/{id}` | Get a product by ID |
| PUT | `/products/{id}` | Update a product |
| DELETE | `/products/{id}` | Delete a product |

## Run Locally

### Prerequisites

- Java 17
- Maven, or use the included Maven wrapper

From the repository root:

```bash
cd product-service
./mvnw spring-boot:run
```

On Windows:

```powershell
cd product-service
mvnw.cmd spring-boot:run
```

The H2 console is enabled at:

```text
http://localhost:8081/h2-console
```

The configured JDBC URL is:

```text
jdbc:h2:mem:testdb
```

## What This Project Demonstrates

This project demonstrates backend fundamentals that are central to Java microservice development:

- REST API design with Spring Boot
- Controller/service/repository separation
- JPA-based persistence
- CRUD workflows
- Maven dependency management
- A foundation for Kafka-based event-driven communication and inter-service HTTP calls

## Roadmap

- Add the Cart Service
- Implement service-to-service communication
- Integrate Kafka producers and consumers
- Add DTOs and validation
- Add centralized exception handling
- Add unit and integration tests
- Add Docker support
- Add API documentation with OpenAPI/Swagger
- Add CI/CD with GitHub Actions

## Status

**In progress.** The Product Service is implemented and the repository is being expanded into a multi-service application.
