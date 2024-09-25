package com.chatter.com.java_assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatter.com.java_assignment.entity.Transactions;
import com.chatter.com.java_assignment.model.CustomerRewardPointResponse;
import com.chatter.com.java_assignment.service.TransactionRewardPointService;
import com.chatter.com.java_assignment.service.TransactionRewardServiceImplementation;

@RestController
public class CustomerRewardsTransactionController {
	
	@Autowired
	private TransactionRewardServiceImplementation transactionRewardServiceImplementation;
	
	@Autowired
	private TransactionRewardPointService transactionRewardPointService;
	
	/**
	 * This method used to create 
	 * customersTransaction
	 * 
	 * @return
	 */
	@PostMapping("/createCustomerTransactions")
	public ResponseEntity<String> createTransaction(@RequestBody Transactions transaction) {
		try {
			transactionRewardServiceImplementation.calculateAndStoreRewardPoints(transaction.getCustomerId(),
					transaction.getAmount(), transaction.getTransactionDate());
			return ResponseEntity.ok("Transaction created successfully !!");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing the request");
		}

	}
	
	/**
	 * This method used to get all 
	 * customersTransaction
	 * 
	 * @return
	 */
	@GetMapping("/getAllCustomers")
	public ResponseEntity<Object> getAllCustomerTransactions() {
		try {
			System.out.println("-----------getAllCustomers----------");
			List<Object> rewardData = transactionRewardPointService.getRecordsForAllCustomers();
			System.out.println(rewardData);
			return ResponseEntity.ok(rewardData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing the request");
		}
	}
	
	
	/**
	 * This method used to sepecific 
	 * getCustomerReqardsPoint
	 * 
	 * @return
	 */
	@GetMapping("/getCustomerReqardsPoint/{customerId}")
	public ResponseEntity<Object> getRewardPointsBasedOnCustomerId(@PathVariable String customerId) {
		try {
			Long custId = Long.parseLong(customerId);
			if (custId <= 0) {
				throw new IllegalArgumentException("Customer ID must be a positive number");
			}
			CustomerRewardPointResponse response = transactionRewardPointService.getCustomerRewardPoints(custId);
			  // Wait for the asynchronous task to complete
			return ResponseEntity.ok(response);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("Invalid customer ID format");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing the request");
		}
	}
	

}
