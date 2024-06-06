-- Create the database if not exists
CREATE DATABASE IF NOT EXISTS shdeploy;

-- Use the shdeploy database
USE shdeploy;

-- Create the project table
CREATE TABLE IF NOT EXISTS project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    path_project VARCHAR(255),
    path_app VARCHAR(255),
    git VARCHAR(255) NOT NULL,
    ip_port VARCHAR(255),
    service INT,
    init INT,
    deploy INT,
    ativo INT,
    programming_language VARCHAR(50) NOT NULL
);

-- Create the registry_action table
CREATE TABLE IF NOT EXISTS registry_action (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    project_id BIGINT,
    action_name VARCHAR(255) NOT NULL,
    date_executed DATETIME NOT NULL,
    user VARCHAR(100) NOT NULL,
    CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES project(id)
);
