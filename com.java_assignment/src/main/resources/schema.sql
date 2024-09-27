----- create Customers and transitions table schema -------------
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
	id INT PRIMARY KEY,
    customer_name VARCHAR(255) 
);

DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions (
	id INT PRIMARY KEY,
	customer_id INT,
    transaction_date Date,
    amount DECIMAL(10,2),
    FOREIGN KEY(customer_id) REFERENCES customer(id)  
);