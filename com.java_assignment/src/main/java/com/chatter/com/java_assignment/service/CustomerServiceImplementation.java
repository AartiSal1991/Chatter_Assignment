package com.chatter.com.java_assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatter.com.java_assignment.entity.Customer; 
import com.chatter.com.java_assignment.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public int addCustomer(Customer customer) {
		System.out.println("--------"+customer.toString());
		Customer customerdata = new Customer();
	 
		customerdata.setCustomer_name(customer.getCustomer_name()); 
		customerRepository.save(customerdata);
		System.out.println("--------");
		return 1;
	}

	//Save Customer
	@Override
	public Customer saveCustomer(Customer cust) {
		customerRepository.save(cust);
		return cust;
	}
	
	
	//Fetching Customers by Id
		@Override
		public Customer getCustomerById(Long custId) {
			    Customer cust= customerRepository.findById(custId).orElseThrow();
				return cust;
		}

}
