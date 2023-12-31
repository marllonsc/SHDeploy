-- Create the database
CREATE DATABASE IF NOT EXISTS shdeploy;

-- Use the shdeploy database
USE shdeploy;

-- Create the project table
CREATE TABLE project (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    path_project VARCHAR(255),
    path_app VARCHAR(255),
    programming_language VARCHAR(50) NOT NULL,
    git VARCHAR(255) NOT NULL,
    ip_port VARCHAR(255),
    service INT,
    init INT,
    deploy INT
);
