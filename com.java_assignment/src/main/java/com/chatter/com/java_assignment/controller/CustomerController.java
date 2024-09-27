/**
 * This is the controller class which contains methods to create and fetch the customer rewards 
 */
package com.chatter.com.java_assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatter.com.java_assignment.entity.Customer;
import com.chatter.com.java_assignment.service.CustomerService;
 
@RestController
public class CustomerController {
 

	
	@Autowired
	private CustomerService customerService;
	 
	/**
	 * This method used to create customer
	 * customers
	 * 
	 * @return
	 */
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
		 
		try {
			   return ResponseEntity.ok().body(customerService.saveCustomer(customer));
		    }
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Adding Customer");
			}
		 
	 

	}
	
	 
}
