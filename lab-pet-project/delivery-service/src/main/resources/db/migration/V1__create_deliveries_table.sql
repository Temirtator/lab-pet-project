CREATE SCHEMA IF NOT EXISTS delivery;

CREATE TABLE delivery.deliveries (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    address TEXT NOT NULL,
    status VARCHAR(32) NOT NULL
);
