# Mini Project - Backend

This is the backend part of the mini project for learning purposes. It is built with Spring Boot, MyBatis, and MySQL.

## Features

- RESTful API
- Database integration with MyBatis
- File upload functionality
- Cross-domain support
- Form submission handling

## Prerequisites

- JDK 1.8 or later
- Maven 3.2+
- MySQL 5.6+

## Getting Started

### Database Setup

1. Create a MySQL database named `mini_project`
2. Run the SQL script in `src/main/resources/db/init.sql`

### Configuration

Modify the database connection settings in `src/main/resources/application.properties` if needed:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mini_project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
```

### Running the Application

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080/api`

## API Endpoints

### User APIs

- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users/register` - Register a new user
- `POST /api/users/login` - User login
- `PUT /api/users/{id}` - Update user information
- `PUT /api/users/{id}/password` - Update user password
- `POST /api/users/{id}/avatar` - Upload user avatar

### Task APIs

- `GET /api/tasks` - Get all tasks
- `GET /api/tasks/{id}` - Get task by ID
- `GET /api/tasks/user/{userId}` - Get tasks by user ID
- `POST /api/tasks` - Create a new task
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Delete task
- `POST /api/tasks/{id}/attachment` - Upload task attachment 