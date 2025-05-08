CREATE DATABASE IF NOT EXISTS contact_registry;
USE contact_registry;

CREATE TABLE IF NOT EXISTS contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email_address VARCHAR(100),
    id_number VARCHAR(20),
    date_of_birth DATE,
    gender VARCHAR(10),
    county VARCHAR(50),
    organization_name VARCHAR(100)
);
