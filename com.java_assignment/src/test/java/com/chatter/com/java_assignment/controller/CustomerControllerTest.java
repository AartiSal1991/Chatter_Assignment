package com.chatter.com.java_assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

 

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
/*
 * @ActiveProfiles("test")
 * 
 * @TestPropertySource(properties =
 * {"spring.config.location=classpath:application-test.properties"})
 */
public class CustomerControllerTest {
@Autowired
private TestRestTemplate testRestTemplate;


@Test
public void testSaveCustomer() {
	Customer cust= new Customer();
	cust.setId((long) 1);
	cust.setCustomer_name("Raj"); 
	assertNotNull(cust);
	ResponseEntity<Customer> response= testRestTemplate.postForEntity("/createCustomer", cust, Customer.class);
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertEquals(cust.getCustomer_name(), response.getBody().getCustomer_name());
}

 
}
