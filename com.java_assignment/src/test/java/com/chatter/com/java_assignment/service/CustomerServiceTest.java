package com.chatter.com.java_assignment.service;
 


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.chatter.com.java_assignment.entity.Customer;

 


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class CustomerServiceTest {
	@Autowired 
	private CustomerService customerService;

	
	@Test
	public void testCreateCustomer() {
		Customer cust=new Customer();
		cust.setId(5L);
		cust.setCustomer_name("Aarti"); 
		Customer newCust=customerService.saveCustomer(cust);
		assertNotNull(newCust.getId());
		assertEquals(cust.getCustomer_name(), newCust.getCustomer_name());
		
	}
	 
	
	}


