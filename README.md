🧠 STRICT RULE
👉 DO NOT MOVE to next step unless:
Current step is fully working
Code is committed to GitHub 
ENTERPRISE JAVA MICROSERVICES EXERCISE

🎯 Objective
In this exercise, you will build a real-world microservices system similar to what is used in:
E-commerce platforms (Amazon-like cart system)
Banking/payment systems (transaction + event processing)
You will implement:
Layered architecture (Controller → Service → Repository → Entity)
SQL Server integration
Inter-service communication using Spring WebClient
Kafka (Producer + Consumer)
Async programming using CompletableFuture
Java Streams
Validation, Exception Handling, Logging
Pagination, Sorting, Native Queries

🏗️ System Overview
You will build 2 microservices:

🔹 Product Service
Responsible for:
Product CRUD operations
Database interaction
Kafka Consumer (acts as notification/logging system)

🔹 Cart Service
Responsible for:
Cart operations
Calling Product Service
Kafka Producer (publishes events)

🔄 Flow
Client → Cart Service → Product Service → Database
                       ↓
                    Kafka Topic
                       ↓
                Product Service (Consumer)

📁 Project Structure
microservices-exercise/
│
├── product-service/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│
├── cart-service/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│
├── README.md

🧩 IMPORTANT RULES
❌ RestTemplate is NOT allowed
✅ MUST use WebClient
✅ Follow layered architecture strictly
❌ Controller must NOT call Repository directly
✅ Each step must be committed separately
✅ Each step must be fully working before moving forward

🧩 STEP-BY-STEP IMPLEMENTATION

✅ 1A – Project Setup
📌 Commit: 1A-setup
What to do:
Create two Spring Boot projects:
product-service (port 8081)
cart-service (port 8082)
Add dependencies:
Spring Web
Spring Data JPA
SQL Server Driver
Lombok

✅ Expected Outcome:
Both applications start successfully
No errors in console
Ports are working

✅ 1B – Entity Layer
📌 Commit: 1B-entity-layer
What to do:
Create Entities:
Product Entity
id
name
price
stock
Cart Entity
id
userId
CartItem Entity
id
cartId
productId
quantity

✅ Expected Outcome:
Tables are created in SQL Server
Application starts without errors

✅ 1C – Repository Layer
📌 Commit: 1C-repository-layer
What to do:
Create repositories extending JPA:
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

✅ Expected Outcome:
No startup errors
Repository beans created successfully

✅ 1D – Service Layer
📌 Commit: 1D-service-layer
What to do:
Implement business logic here
Do NOT write logic in controller
Example Responsibilities:
Create product
Get product
Validate stock

✅ Expected Outcome:
Service methods compile
Logic separated from controller

✅ 1E – Controller Layer (CRUD APIs)
📌 Commit: 1E-controller-crud
What to do:
Expose REST APIs:
POST → create product
GET → fetch product(s)
PUT → update product
DELETE → remove product

✅ Expected Outcome:
APIs work via Postman
Data is saved and retrieved from DB

✅ 1F – Pagination + Sorting + Java Streams
📌 Commit: 1F-paging-sorting-streams

✔ Pagination & Sorting
Implement API with:
Page number
Page size
Sort field

✔ Java Streams
Use streams for:
Filtering products
Transforming data

✅ Expected Outcome:
API returns paginated data
Sorting works correctly

✅ 1G – Native Query
📌 Commit: 1G-native-query

What to do:
Write a native SQL query:
Example: fetch products above certain price

✅ Expected Outcome:
API returns correct filtered data
Native query works correctly

✅ 1H – WebClient Integration
📌 Commit: 1H-webclient

What to do:
From Cart Service:
Call Product Service
Fetch product details

Validate:
Product exists
Stock is sufficient

✅ Expected Outcome:
Cart service successfully calls Product Service
Validation works correctly

✅ 1I – Kafka Producer (Cart Service)
📌 Commit: 1I-kafka-producer

What to do:
When product added to cart:
Publish event to Kafka

Event Example:
{
 "cartId": 1,
 "productId": 101,
 "quantity": 2
}

✅ Expected Outcome:
Kafka topic receives message

✅ 1J – Kafka Consumer (Product Service)
📌 Commit: 1J-kafka-consumer

What to do:
Listen to Kafka topic
Log received events

✅ Expected Outcome:
Events are consumed and logged

✅ 1K – CompletableFuture (Async Processing)
📌 Commit: 1K-completablefuture

What to do:
Run in parallel:
Fetch product
Validate stock

✅ Expected Outcome:
Parallel execution works
No blocking behavior

✅ 1L – Validation + Exception + Logging
📌 Commit: 1L-validation-logging

✔ Validation
Quantity should not be null
Quantity must be > 0

✔ Exception Handling
Use global exception handler

✔ Logging
Log:
API calls
Errors
Kafka events

✅ Expected Outcome:
Validation errors handled properly
Logs printed clearly




🧾 PART 2 – REACT 18 + REDUX ENTERPRISE UI EXERCISE

🎯 Objective
Build a production-ready frontend application using modern React that integrates with your backend microservices (Product + Cart Services).
This exercise simulates a real enterprise UI layer used in:
E-commerce systems
Banking dashboards
Inventory systems

🧱 TECH STACK (MANDATORY)
You MUST use:
React (v18+)
Redux Toolkit
Axios
React Router (v6+)
Functional Components + Hooks ONLY

🚨 STRICT RULES
❌ No class components
❌ No direct API calls inside components
❌ No business logic inside UI components
✅ Use Redux Toolkit for state
✅ Use Axios for API calls
✅ Use Hooks (useEffect, useMemo, useCallback)
✅ Each step must be committed separately
✅ Each step must be working before moving forward

📁 PROJECT STRUCTURE (MANDATORY)
src/
│
├── app/            → Redux store
├── features/       → Redux slices
├── pages/          → Screens (Product, Cart)
├── components/     → Reusable UI
├── services/       → Axios API layer
├── hooks/          → Custom hooks
├── routes/         → Routing config
├── utils/          → Helper functions

🧩 EXERCISE STEPS (WITH COMMITS)

✅ 2A – React 18 Setup
📌 Commit: 2A-setup
Tasks:
Create React app using Vite
Setup folder structure
Install dependencies:
axios
redux toolkit
react-redux
react-router-dom

✅ MUST WORK:
App runs successfully
No console errors

✅ 2B – Product UI (List + Create)
📌 Commit: 2B-product-ui

Build:
Pages:
Product List Page
Add Product Page

Features:
Fetch products from backend
Display in table
Create new product

✅ MUST WORK:
Products display correctly
Product creation works

✅ 2C – Redux Toolkit Integration
📌 Commit: 2C-redux

Tasks:
Create Redux store
Create product slice using createSlice
Use createAsyncThunk for API calls

✅ MUST WORK:
Data stored in Redux
UI reads data from Redux (NOT API directly)

✅ 2D – Cart UI + Integration
📌 Commit: 2D-cart

Features:
Add to Cart button
Cart Page

Flow:
UI → Cart API → Kafka → Product Service

✅ MUST WORK:
Cart API called successfully
Cart items displayed

🚨 MANDATORY ENTERPRISE FEATURES

✅ 2E – Loading Spinner + Error Handling
📌 Commit: 2E-loading-error

Requirements:
✔ Loading State
Show spinner while API is loading
✔ Error Handling
Show error message if API fails

✅ MUST WORK:
Spinner appears during API calls
Errors displayed properly

✅ 2F – Search + Filter (useMemo REQUIRED)
📌 Commit: 2F-search-filter

Features:
Search by product name
Filter by price

MUST USE:
useMemo for optimization

✅ MUST WORK:
Real-time filtering
No unnecessary re-renders

✅ 2G – Pagination UI
📌 Commit: 2G-pagination

Features:
Page navigation (Next / Prev)
Page numbers

Integration:
Use backend pagination API

✅ MUST WORK:
Pagination works correctly
UI updates based on page

✅ 2H – Routing (React Router v6)
📌 Commit: 2H-routing

Routes:
/products → Product List
/add-product → Add Product
/cart → Cart Page

✅ MUST WORK:
Navigation works without reload
All pages accessible

✅ 2I – Custom Hooks + Clean Architecture
📌 Commit: 2I-hooks-clean

Tasks:
Create reusable hooks (e.g., useProducts)
Move logic out of components

Enforce Separation:
Layer
Responsibility
Components
UI only
Redux
State
Services
API
Hooks
Logic


✅ MUST WORK:
No API calls inside components
Clean separation maintained 

