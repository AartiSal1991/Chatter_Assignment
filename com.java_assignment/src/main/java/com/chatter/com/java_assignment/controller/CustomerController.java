/**
 * This is the controller class which contains methods to create and fetch the customer rewards 
 */
package com.chatter.com.java_assignment.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatter.com.java_assignment.entity.Customer;
import com.chatter.com.java_assignment.repository.CustomerRepository;
 
@RestController
public class CustomerController {
 

	
	@Autowired
	private CustomerRepository customerRepository;
	 
	/**
	 * This method used to create customer
	 * customers
	 * 
	 * @return
	 */
	
	@PostMapping("/createCustomer")
	public Customer createCustomer(@RequestBody Customer customer) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			System.out.println(customer);
			customerRepository.save(customer);
			 
			
		}
		catch (Exception e){
			map.put("status", 0);
			map.put("message", "Customer Not Saved");
			
		}
		return customer;
		 
	 

	}
	
	 
}
