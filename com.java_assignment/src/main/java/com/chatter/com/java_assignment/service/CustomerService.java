package com.chatter.com.java_assignment.service;

import com.chatter.com.java_assignment.entity.Customer;

public interface CustomerService {

	/**
	 * contains the logic to calculate the reward points
	 * 
	 */
	public int addCustomer(Customer customer);
	Customer saveCustomer(Customer cust);
}
