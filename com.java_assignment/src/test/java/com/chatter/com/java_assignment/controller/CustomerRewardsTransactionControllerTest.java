package com.chatter.com.java_assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.chatter.com.java_assignment.entity.Customer;
import com.chatter.com.java_assignment.entity.Transactions;

 

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
/*
 * @ActiveProfiles("test")
 * 
 * @TestPropertySource(properties =
 * {"spring.config.location=classpath:application-test.properties"})
 */
public class CustomerRewardsTransactionControllerTest {
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void testSaveTransaction() {
		Transactions transaction= new Transactions();
		transaction.setId(12L);
		Customer cust = new Customer();
		cust.setId(1L);
		cust.setCustomer_name("Aarti");
		transaction.setCustomer(cust);
		transaction.setAmount((double) 100);
		transaction.setTransactionDate(new Date());
		
		ResponseEntity<Transactions> response= testRestTemplate.postForEntity("/createCustomerTransactions", transaction, Transactions.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(transaction.getAmount(), response.getBody().getAmount());
	}

 
	}
 
