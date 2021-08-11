DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), price int, PRIMARY KEY (id));
INSERT INTO products (title, price) VALUES ('milk', 10),('coffee', 20),('tee', 30),('juice', 40),('water', 50);

DROP TABLE customers IF EXISTS;
CREATE TABLE IF NOT EXISTS customers (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO customers (name) VALUES ('Bob'),('Bill'),('Jack');

DROP TABLE customers_products IF EXISTS;
CREATE TABLE IF NOT EXISTS customers_products (customer_id bigint, product_id bigint, FOREIGN KEY (customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO customers_products (customer_id, product_id) VALUES (1, 1),(1, 2),(1, 3),(2, 1),(2, 3),(3, 1);

DROP TABLE orders IF EXISTS;
CREATE TABLE IF NOT EXISTS orders (order_id bigserial, customer_id bigint, product_id bigint, price int, PRIMARY KEY (order_id),FOREIGN KEY (customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO orders (customer_id, product_id, price) VALUES (1,1,10),(1,2,20),(1,3,30),(1,1,100),(1,2,200),(1,3,300),(2,1,1000),(2,2,2000),(2,3,3000);