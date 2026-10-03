# Microservices Project

A Java/Spring Boot backend project with two independently runnable services: **Product Service** and **Cart Service**.

The project demonstrates REST API design, service-to-service communication, validation, persistence, exception handling, automated tests, containerization, and CI.

## Architecture

```text
Client
  |
  +--------------------+
  |                    |
  v                    v
Product Service      Cart Service
:8081                :8082
  |                    |
  |                    +---- REST lookup ----> Product Service
  v                    v
H2 Product DB        H2 Cart DB
```

The Cart Service validates requested products against Product Service before adding them to the cart.

## Services

### Product Service

Responsibilities:

- Create, retrieve, update, and delete products
- Validate product name, price, and inventory
- Return proper HTTP status codes
- Return structured validation and not-found errors
- Persist product data through Spring Data JPA

Base URL:

```text
http://localhost:8081/products
```

| Method | Endpoint | Purpose |
| --- | --- | --- |
| POST | `/products` | Create a product |
| GET | `/products` | Get all products |
| GET | `/products/{id}` | Get a product |
| PUT | `/products/{id}` | Update a product |
| DELETE | `/products/{id}` | Delete a product |

### Cart Service

Responsibilities:

- Add products to a cart
- Retrieve cart items
- Remove individual cart items
- Clear the cart
- Look up product information from Product Service
- Reject quantities above available product inventory

Base URL:

```text
http://localhost:8082/cart/items
```

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET | `/cart/items` | Get cart items |
| POST | `/cart/items` | Add an item |
| DELETE | `/cart/items/{id}` | Remove an item |
| DELETE | `/cart/items` | Clear the cart |

Example request:

```json
{
  "productId": 1,
  "quantity": 2
}
```

## Tech Stack

- Java 17
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- Bean Validation
- Spring RestClient
- H2
- Maven
- JUnit 5
- Mockito
- Docker
- GitHub Actions

## Run Locally

Start Product Service:

```bash
cd product-service
./mvnw spring-boot:run
```

Start Cart Service in another terminal:

```bash
cd cart-service
mvn spring-boot:run
```

## Run with Docker Compose

From the repository root:

```bash
docker compose up --build
```

Product Service will be available at port `8081`, and Cart Service at port `8082`.

## Testing

Product Service:

```bash
cd product-service
./mvnw test
```

Cart Service:

```bash
cd cart-service
mvn test
```

Both services are also tested automatically by GitHub Actions on pull requests.

## Project Structure

```text
Microservices-Project/
├── product-service/
├── cart-service/
├── docker-compose.yml
└── .github/workflows/
```

## Engineering Improvements Included

- Constructor injection instead of field injection
- Explicit not-found handling instead of returning null
- Bean Validation at API boundaries
- Structured REST responses
- Unit tests for service behavior
- Service-to-service product validation
- Multi-stage Docker images
- CI for both services

## Next Engineering Steps

- Introduce DTO mapping in Product Service
- Add API documentation with OpenAPI
- Add integration tests across both services
- Introduce Kafka only when there is a concrete event-driven use case
- Move from in-memory H2 databases to persistent databases for deployed environments
- Add observability and centralized logging
