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
- schema.sql(table creation schema for Customer and transactions) 

2. Used h2 DB , created two tables Customer and  Transactions where customer id used as foreign key. </br> 
 
Schema:
----- create Customer and transitions table schema -------------</br>
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
	id INT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);
</br>
DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions (
	id INT PRIMARY KEY,
	customer_id INT,
    transaction_date Date,
    amount DECIMAL(10,2),
    FOREIGN KEY(customer_id) REFERENCES customer(id)  
);</br>  

3. Created below Apis </br>

http://localhost:8080/createCustomer : CREATE CUSTOMER  - POST CALL 

Data:
{
  "id":1,
  "customer_name": "Aarti" 
}

#API Response for create customer

{
    "id": 1,
    "customer_name": "Aarti"
}</br>

<b>http://localhost:8080/createCustomerTransactions
{
    "id": 1,
    "customer": {
      "id": 1,
      "customer_name": "Aarti" 
    },
    "transactionDate": "2024-09-14",
    "amount": 100
}

#API Response for create customer Transactions

{
    "id": 1,
    "customer": {
        "id": 1,
        "customer_name": "Aarti"
    },
    "transactionDate": "2024-09-14",
    "amount": 100.0
}</br>


#API Response for monthly rewards get CALL
http://localhost:8080//monthlyRewardCustomer/1/9
{
    "monthlyPoints": {
        "Oct": 50,
        "Aug": 800
    },
    "totalPoints": 850
}

#API Response for total rewards get CALL
http://localhost:8080/totalRewardsCustomer/-1
{
Customer ID must be a positive number
}
<br>
4. Junit testcases written for controller and Services using TestRestTemplate</br> 