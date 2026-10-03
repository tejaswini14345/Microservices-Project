# Microservices Project

A Java 17 / Spring Boot microservices project with separate **Product** and **Cart** services, REST APIs, validation, tests, Docker support, and GitHub Actions CI.

## Architecture

```text
Client
  |
  +--> Product Service :8081
  |      - Product CRUD
  |      - Validation
  |      - H2 persistence
  |
  +--> Cart Service :8082
         - Cart item management
         - Calls Product Service for product details
         - Inventory-aware quantity checks
         - H2 persistence
```

The Cart Service uses the Product Service URL from `PRODUCT_SERVICE_URL`, which defaults to `http://localhost:8081`.

## Services

### Product Service

Base URL: `http://localhost:8081/products`

| Method | Endpoint | Purpose |
| --- | --- | --- |
| POST | `/products` | Create a product |
| GET | `/products` | Get all products |
| GET | `/products/{id}` | Get a product |
| PUT | `/products/{id}` | Update a product |
| DELETE | `/products/{id}` | Delete a product |

Highlights:
- Bean Validation for product requests
- Centralized validation and 404 handling
- Proper REST status codes
- Spring Data JPA persistence
- Unit tests with Mockito

### Cart Service

Base URL: `http://localhost:8082/cart/items`

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET | `/cart/items` | Get cart items |
| POST | `/cart/items` | Add a product to the cart |
| DELETE | `/cart/items/{id}` | Remove a cart item |
| DELETE | `/cart/items` | Clear the cart |

When an item is added, the Cart Service retrieves the product from Product Service and stores product name, unit price, and requested quantity.

## Tech Stack

- Java 17
- Spring Boot 3.5.x
- Spring Web / RestClient
- Spring Data JPA
- Bean Validation
- H2
- Maven
- Lombok
- JUnit 5
- Mockito
- Docker / Docker Compose
- GitHub Actions

The Product Service also includes dependencies for Kafka/WebClient as the project continues evolving toward more event-driven communication.

## Run with Docker Compose

From the repository root:

```bash
docker compose up --build
```

Services:
- Product Service: `http://localhost:8081`
- Cart Service: `http://localhost:8082`

## Run Locally

### Product Service

```bash
cd product-service
chmod +x mvnw
./mvnw spring-boot:run
```

### Cart Service

```bash
cd cart-service
mvn spring-boot:run
```

## Testing

```bash
cd product-service
chmod +x mvnw
./mvnw test
```

```bash
cd cart-service
mvn test
```

Both services are also verified through GitHub Actions on pull requests and pushes to the default branch.

## Next Improvements

- Add API gateway / service discovery
- Add Kafka-based domain events
- Replace local H2 databases with persistent production databases
- Add distributed tracing and centralized logging
- Add OpenAPI documentation
- Expand integration and contract testing
