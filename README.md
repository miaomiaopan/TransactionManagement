# Transaction Management System

A RESTful web service built with Spring Boot that provides transaction management capabilities.

## Overview

This is a simple transaction management system which provides add,edit,delete and search functions.

## Features

- Create a new transaction
- Modify existing transactions
- Delete transactions by ID
- List all transactions

## Technologies

- Java
- Spring Boot
- Junit test

## API Endpoints

### Transaction Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/transaction` | Create a new transaction |
| PUT | `/transaction/{id}` | Modify an existing transaction |
| DELETE | `/transaction/{id}` | Delete a transaction |
| GET | `/transaction` | List all transactions |

## Getting Started

### Prerequisites

- Java JDK 21
- Maven
- IDE

### How to use

1. Clone the repository
2. Open the project with IDE
3. Config jdk and maven
4. Run the project
4. Check the API Using Swagger(http://localhost:9081/swagger-ui/index.html)

### Testing
#### TransactionControllerTest.class
The class includes test suites for controller.

#### PerformanceTest.java
The class includes performance for creating transaction.

### TODO
#### 1. adding Dockerfile
We can use a Dockerfile to create a Docker image and upload the image to Docker Hub.

#### 1. adding test suites for service and performance test for searching transactions.

