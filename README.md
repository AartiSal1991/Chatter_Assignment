# Chatter_Assignment

1.1 Customer Reward Point Calculation have customer and transaction CRUD apis along with Reward calculation APIS
1.2 Classes in application
a. Main Class JavaAssignmentApplication
- Controllers:
- CustomerController
- CustomerRewardsTransactionController 
b. Service interface and class
- CustomerService and CustomerServiceImplementation (CRUD for Customers)
- TransactionRewardPointService and TransactionRewardServiceImplementation and TransactionRewardService (CRUD for transactions and Rewards) 
c. Entity  
- Customer
- Transactions
d. Repository
- CustomerRepository
- TransactionRepository
1.3 Configuration Files
- application.properties (DB details/credentials and application configurations)
- schema.sql(table creation schema for Customers and transactions) 

Used h2 DB , created two tables Customers and transactions  

Created below 4 Apis ,
http://localhost:8080/createCustomer : CREATE CUSTOMER  - POST CALL
 
Basic validations applied for Cust id , transaction id and yearMonth
 
Data Used for testing
Schema:----- create Customers and transitions table schema -------------
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


#API Response for create customer


API responses:<br>
<b>http://localhost:8080/createCustomer<b><br>
[
  {
    "id": 1,
    "customer_name": "Aarti" 
  },
  {
    "id": 2,
    "customer_name": "Jaya" 
  } 
]<br>
<b>http://localhost:8080/createCustomerTransactions
{
    "id": 1,
    "customer": {
      "id": 1,
      "customer_name": "Ram" 
    },
    "transactionDate": "2024-09-14",
    "amount": 100
  }



http://localhost:8080//monthlyRewardCustomer/1/9
{
    "monthlyPoints": {
        "Oct": 50,
        "Aug": 800
    },
    "totalPoints": 850
}


http://localhost:8080/totalReward/-1
{
Customer ID must be a positive number
}