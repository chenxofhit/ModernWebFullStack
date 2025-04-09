-- Create the database
CREATE DATABASE IF NOT EXISTS mini_project;

USE mini_project;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    avatar VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create tasks table
CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    status ENUM('TODO', 'IN_PROGRESS', 'DONE') DEFAULT 'TODO',
    user_id INT NOT NULL,
    attachment VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert sample users
INSERT INTO users (username, password, email)
VALUES 
    ('admin', '123456', 'admin@example.com'),
    ('user1', '123456', 'user1@example.com');

-- Insert sample tasks
INSERT INTO tasks (title, description, status, user_id)
VALUES 
    ('Complete project', 'Finish the mini project', 'TODO', 1),
    ('Learn Vue 3', 'Study Vue 3 composition API', 'IN_PROGRESS', 1),
    ('Learn Spring Boot', 'Master Spring Boot fundamentals', 'DONE', 2); 