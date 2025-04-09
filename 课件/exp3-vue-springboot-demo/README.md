# Vue + Spring Boot Mini Project

A complete mini front-end and back-end separated project for educational purposes. This project demonstrates how to build a modern web application with Vue 3 on the frontend and Spring Boot, MyBatis, and MySQL on the backend.

## Project Overview

This is a simple task management system with user authentication, task CRUD operations, and file uploads. It demonstrates key aspects of modern web development:

- Front-end and back-end separation
- RESTful API design
- Database integration
- Cross-domain issues
- Form handling
- File uploads
- Authentication

## Project Structure

The project is divided into two main parts:

- `frontend/` - Vue 3 frontend project
- `backend/` - Spring Boot backend project

## Features

### Backend
- Spring Boot 2.7.x
- MyBatis for database access
- MySQL database
- File upload handling
- Cross-domain support
- RESTful API

### Frontend
- Vue 3 with Composition API
- Pinia for state management
- Vue Router for routing
- ElementPlus UI library
- Axios for API requests
- File upload handling
- Form validation

## Getting Started

### Prerequisites

- Java 8+
- Maven 3.6+
- Node.js 14+
- MySQL 5.7+

### Database Setup

1. Create a MySQL database named `mini_project`
2. Run the SQL script in `backend/src/main/resources/db/init.sql`

### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Build and run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

The backend server will start on `http://localhost:8080/api`

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   # or
   yarn install
   ```

3. Run the development server:
   ```bash
   npm run dev
   # or
   yarn dev
   ```

The frontend app will start on `http://localhost:5173`

## API Documentation

See the backend README.md for detailed API documentation.

## Learning Points

This project demonstrates:

1. Setting up a Spring Boot project with MyBatis
2. Creating a Vue 3 application with Composition API
3. Implementing RESTful API with Spring Boot
4. Managing application state with Pinia
5. Form handling and validation
6. File upload on both frontend and backend
7. Handling cross-domain issues
8. User authentication
9. Database integration

## License

This project is available for educational purposes. Feel free to use it to learn or teach web development. 