CREATE TABLE IF NOT EXISTS products(
    id serial PRIMARY KEY,
    product_name varchar(255) unique not null,
    price float not null,
    discount boolean
    );