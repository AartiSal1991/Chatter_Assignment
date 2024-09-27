package com.chatter.com.java_assignment.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatter.com.java_assignment.entity.Transactions;
import com.chatter.com.java_assignment.service.TransactionRewardPointServiceImpl;
 

@RestController
public class CustomerRewardsTransactionController {
	
 
	
	@Autowired
	private TransactionRewardPointServiceImpl transactionRewardPointService;
	
	public static final Pattern YearMonth_pattern= Pattern.compile("^[0-9]{4}-[0-9]{2}$");
	
	/**
	 * This method used to create 
	 * customersTransaction
	 * 
	 * @return
	 */
	@PostMapping("/createCustomerTransactions")
	public ResponseEntity<Object> createTransaction(@RequestBody Transactions transaction) {
		try {
			   return ResponseEntity.ok().body( transactionRewardPointService.createCustomerTransactions(transaction));
		    }
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Adding Transactions");
			}

	}
	
	  
	// Total rewards for given customer id 
		@GetMapping("/totalRewardsCustomer/{custId}")
		public ResponseEntity<Object> calculateTotalRewardPointsByCustomerId(@PathVariable Long custId) {
			try {
				if (custId <= 0) {
					throw new IllegalArgumentException("Customer ID must be a positive number");
				}
				 
				   Map<Long, Integer> result = new HashMap<Long, Integer>();
				   result.put(custId, transactionRewardPointService.calculateTotalRewardPointsByCustomerId(custId));
				   return ResponseEntity.ok().body(result);			   
			    }
			    catch (NumberFormatException e) {
				  return ResponseEntity.badRequest().body("Invalid customer ID format");
			    }
			    catch (IllegalArgumentException e) {
			    	return ResponseEntity.badRequest().body(e.getMessage());
				}
			    
				catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Error occured while Calculating Total reward Points By Customer Id");
				}
		}
		
		//monthly rewards for given customer 
		@GetMapping("/monthlyRewardCustomer/{custId}/{yearMonth}")
		public ResponseEntity<Object> calculateMonthlyRewardPointsByCustomerId(@PathVariable Long custId,@PathVariable String yearMonth) {
			try {
				if (custId <= 0) {
					throw new IllegalArgumentException("Customer ID must be a positive number");
				}
				if(!validateYearMonth(yearMonth)) {
					throw new IllegalArgumentException("Please provide Valid year Month YYYY-MM eg.2024-07");
				}
				   Map<String, Integer> result = new HashMap<String, Integer>();
				   result.put(custId+":"+yearMonth, transactionRewardPointService.calculateMonthlyRewardPointsByCustomerId(custId,yearMonth));
				   return ResponseEntity.ok().body(result);
			    }
			    catch (NumberFormatException e) {
				  return ResponseEntity.badRequest().body("Invalid customer ID or YearMonth format,for Customer ID use number and for year and Month use YYYY-MM format");
			    }
			    catch (IllegalArgumentException e) {
			    	return ResponseEntity.badRequest().body(e.getMessage());
				}
			    
				catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Error occured while Calculating Monthly reward Points By Customer Id");
				}

		}
		
		//Total rewards for all customers
		@GetMapping("/totalRewardAll")
		public ResponseEntity<Object> calculateTotalRewardPointsAllCustomer(){
			try {
				   return ResponseEntity.ok().body(transactionRewardPointService.calculateTotalRewardPointsAllCustomer());
			    }
				catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Error occured while Calculating Total rewards for all customer");
				}
		}
		
		//Monthly rewards for all customers
		@GetMapping("/monthlyRewardAll/{yearMonth}")
		public ResponseEntity<Object> calculateMonthlyRewardPointsAllCustomer(@PathVariable String yearMonth){
			try {
				if(!validateYearMonth(yearMonth)) {
					throw new IllegalArgumentException("Please provide Valid year Month YYYY-MM  eg.2024-07");
				}
				   return ResponseEntity.ok().body(transactionRewardPointService.calculateMonthlyRewardPointsAllCustomer(yearMonth));
			    }
			    catch (IllegalArgumentException e) {
			    	return ResponseEntity.badRequest().body(e.getMessage());
				}
				catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Error occured while Calculating Monthly rewards for all customer");
				}
		}
		//Method for year month validation
		public boolean validateYearMonth(String yearMonth) {
			boolean status=true;
			try {
			String[] yearMonthSplit = yearMonth.split("-");
			int year =Integer.parseInt(yearMonthSplit[0]);
			int month =Integer.parseInt(yearMonthSplit[1]);
			
			if(YearMonth_pattern.matcher(yearMonth).matches()) {
				if(year>LocalDate.now().getYear()) {
					status=false;
				}
				if(month<=0 || month>12)
				{
					status=false;
				}
			}
			else {
				status=false;
			}
			}
			catch (Exception e) {
				status=false;
			}
			return status;
		}
	

}
