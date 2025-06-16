CREATE DATABASE order_service_db;

\c order_service_db;

CREATE TABLE IF NOT EXISTS order_request (
    id BIGSERIAL PRIMARY KEY,
    product_id VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    user_email VARCHAR(255) NOT NULL,
    buyer_id VARCHAR(255) NOT NULL
);

INSERT INTO order_request (product_id, quantity, user_email, buyer_id) VALUES 
('prod_123', 2, 'user1@example.com', 'buyer_456'),
('prod_456', 1, 'user2@example.com', 'buyer_789'),
('prod_789', 3, 'user3@example.com', 'buyer_123');