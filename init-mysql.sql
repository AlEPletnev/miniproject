CREATE DATABASE IF NOT EXISTS app_db;

GRANT ALL PRIVILEGES ON app_db.* TO 'app_user'@'%';
FLUSH PRIVILEGES;

USE app_db;

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    user_email VARCHAR(255) NOT NULL
);

INSERT INTO orders (product_id, quantity, user_email) VALUES
('PROD-1001', 2, 'alex@example.com'),
('PROD-1002', 1, 'maria@example.com'),
('PROD-1003', 5, 'john@example.com'),
('PROD-1004', 3, 'alex@example.com'),
('PROD-1005', 1, 'sarah@example.com'),
('PROD-1006', 4, 'david@example.com'),
('PROD-1007', 2, 'lisa@example.com'),
('PROD-1008', 1, 'peter@example.com'),
('PROD-1009', 10, 'anna@example.com'),
('PROD-1010', 1, 'mike@example.com');