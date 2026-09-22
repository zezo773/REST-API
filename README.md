# REST API

A clean and lightweight user-management REST API built with **Spring Boot**, **Spring Data JPA**, and **MySQL**.

This project demonstrates a layered Java backend with RESTful CRUD operations, DTO-based API models, entity mapping, service-layer business logic, and MySQL persistence.

## Features

- Create users
- Retrieve a user by ID
- Retrieve all users
- Update users
- Delete users
- Persist users in MySQL
- Enforce unique email addresses
- Automatically update the database schema during development
- Return `404 Not Found` for missing users
- Run with the included Maven Wrapper

## Technology Stack

| Technology | Purpose |
| --- | --- |
| Java 25 | Application language |
| Spring Boot 4.1.1 | Application framework |
| Spring Web MVC | REST endpoints and HTTP handling |
| Spring Data JPA | Repository and database access |
| Hibernate | Object-relational mapping |
| MySQL | Relational database |
| Maven | Build and dependency management |
| JUnit 5 | Testing |

## Architecture

The project follows a conventional layered architecture:

```text
HTTP Request
    ↓
UserController
    ↓
UserService
    ↓
UserRepository
    ↓
MySQL
```

Entity-to-DTO conversion is handled by `UserMapper`, keeping persistence models separate from API responses.

## Project Structure

```text
REST-API/
├── src/
│   ├── main/
│   │   ├── java/com/ZIAD/REST_API/
│   │   │   ├── Controller/       REST endpoints
│   │   │   ├── DTO/              API request/response models
│   │   │   ├── Entity/           JPA database entities
│   │   │   ├── Exception/        Custom API exceptions
│   │   │   ├── Mapper/           Entity/DTO conversions
│   │   │   ├── Repository/       Spring Data repositories
│   │   │   ├── Service/           Business logic
│   │   │   └── RestApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/ZIAD/REST_API/
│           └── RestApiApplicationTests.java
├── .mvn/                         Maven Wrapper configuration
├── mvnw                          Maven Wrapper for Linux/macOS
├── mvnw.cmd                      Maven Wrapper for Windows
└── pom.xml                       Maven project configuration
```

## User Model

The API manages users with these fields:

| Field | Type | Description |
| --- | --- | --- |
| `id` | `Long` | Auto-generated primary key |
| `firstName` | `String` | User's first name |
| `lastName` | `String` | User's last name |
| `email` | `String` | Unique email address |

The JPA entity maps to the `users` table.

## API Reference

Base URL:

```text
http://localhost:8080/api/users
```

### Create a user

```http
POST /api/users
```

```json
{
  "firstName": "Ziad",
  "lastName": "Ahmed",
  "email": "ziad@example.com"
}
```

Example:

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Ziad",
    "lastName": "Ahmed",
    "email": "ziad@example.com"
  }'
```

Returns `201 Created` with the saved user.

### Get a user

```http
GET /api/users/{id}
```

```bash
curl http://localhost:8080/api/users/1
```

Returns `200 OK`, or `404 Not Found` when the user does not exist.

### Get all users

```http
GET /api/users
```

```bash
curl http://localhost:8080/api/users
```

Returns a JSON array of users.

### Update a user

```http
PUT /api/users/{id}
```

```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Ziad Updated",
    "lastName": "Ahmed",
    "email": "ziad.updated@example.com"
  }'
```

Returns `200 OK` with the updated user.

### Delete a user

```http
DELETE /api/users/{id}
```

```bash
curl -X DELETE http://localhost:8080/api/users/1
```

Returns `200 OK` with:

```text
Deleted Successfully
```

## Getting Started

### Prerequisites

Install the following software:

- JDK 25
- MySQL 8 or later
- Git

A global Maven installation is optional because this repository includes Maven Wrapper scripts.

### Configure MySQL

Create the database:

```sql
CREATE DATABASE user_management;
```

Create the application user, or use an existing MySQL account:

```sql
CREATE USER 'ziad'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON user_management.* TO 'ziad'@'localhost';
FLUSH PRIVILEGES;
```

Update `src/main/resources/application.properties`:

```properties
spring.application.name=REST-API

spring.datasource.url=jdbc:mysql://localhost:3306/user_management
spring.datasource.username=ziad
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace `YOUR_PASSWORD` with your local database password. Never commit a real password to GitHub.

### Run the application

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bat
mvnw.cmd spring-boot:run
```

The API starts at:

```text
http://localhost:8080
```

### Build the application

Linux/macOS:

```bash
./mvnw clean package
```

Windows:

```bat
mvnw.cmd clean package
```

Run the generated JAR:

```bash
java -jar target/REST-API-0.0.1-SNAPSHOT.jar
```

### Run tests

Linux/macOS:

```bash
./mvnw test
```

Windows:

```bat
mvnw.cmd test
```

The current test suite verifies that the Spring application context loads successfully.

## Configuration Notes

The project currently uses:

```properties
spring.jpa.hibernate.ddl-auto=update
```

This is convenient for development because Hibernate can update the schema from the entity model. For production, use a migration tool such as Flyway or Liquibase.

SQL logging is enabled with:

```properties
spring.jpa.show-sql=true
```

Disable this in production if the SQL output is not needed.

## Development Guidelines

When extending the project:

1. Keep HTTP concerns in `Controller`.
2. Keep business logic in `Service`.
3. Keep persistence operations in `Repository`.
4. Keep database models in `Entity`.
5. Keep API models in `DTO`.
6. Update `UserMapper` whenever entity or DTO fields change.
7. Add tests for new endpoints and business rules.
8. Keep secrets out of source control.

## Recommended Next Improvements

- Add Bean Validation using `@Valid`, `@NotBlank`, and `@Email`
- Add centralized exception handling with `@ControllerAdvice`
- Add endpoint integration tests
- Add pagination, sorting, and filtering
- Add OpenAPI/Swagger documentation
- Use Flyway or Liquibase for database migrations
- Add environment-based configuration profiles
- Add Docker and Docker Compose support
- Add Testcontainers-based MySQL tests
- Add authentication and authorization if this becomes a protected user service

## License

No license has been specified yet. Add a `LICENSE` file before distributing or reusing this project publicly.

## Author

Created and maintained by [zezo773](https://github.com/zezo773).
