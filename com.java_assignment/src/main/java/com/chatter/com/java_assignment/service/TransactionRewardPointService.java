/**
 * This class is used to get the request asynchronous format which helps calling the thread to 
 * continue execute without blocking
 */
package com.chatter.com.java_assignment.service;

import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.chatter.com.java_assignment.entity.Transactions;
import com.chatter.com.java_assignment.model.TransactionRewardPointsForAllCustomer;
import com.chatter.com.java_assignment.model.CustomerRewardPointResponse;
import com.chatter.com.java_assignment.repository.TransactionRepository; 
 
@Service
public class TransactionRewardPointService {

	@Autowired
	private TransactionRepository transactionsRepository;

	@Autowired
	private TransactionRewardServiceImplementation rewardServiceImplementation;

	 
	 
	public List<Object> getRecordsForAllCustomers() {
		
		System.out.println("-----getRecordsForAllCustomers -----");
		
		List<Transactions> transactionsList = transactionsRepository.findAll();
		Map<Long, Map<String, Integer>> customerPoints = new HashMap<>();
		Map<String, Integer> customerSingelPoints = new HashMap<>();
		System.out.println("-----transactionsList -----"+transactionsList);
		for (Transactions transaction : transactionsList) {
			Long customerId = transaction.getCustomerId();
			String month = transaction.getTransactionDate().getMonth().getDisplayName(TextStyle.SHORT,
					Locale.getDefault());
			System.out.println("-----month -----"+month);
			int points = rewardServiceImplementation.calculateRewardPoints(transaction.getAmount());
			System.out.println("-----points -----"+points);
			System.out.println("-----customerSingelPoints -----"+customerSingelPoints);
			// this will map the customer with its ID monthly reward points earned.
			if(customerPoints.isEmpty())
			{
				System.out.println("-----empty check -----");
				customerSingelPoints.put(month, points);
				customerPoints.put(customerId, customerSingelPoints);
				
			}else
			{
				System.out.println("-----customerPoints -----"+customerPoints.get(customerId));
			}
		}

		System.out.println("-----customerPoints -----"+customerPoints);
		
		List<Object> rewardPointForAllCustomerList = customerPoints.entrySet().stream()
				.map(entry -> new TransactionRewardPointsForAllCustomer(entry.getKey(), entry.getValue(),
						entry.getValue().values().stream().mapToInt(Integer::intValue).sum()))
				.collect(Collectors.toList());
		
		System.out.println("-----reqads data -----"+rewardPointForAllCustomerList);

		return rewardPointForAllCustomerList;
	}
	
	
	
	
	public CustomerRewardPointResponse getCustomerRewardPoints(Long customerId) {
		List<Transactions> transactionsList = transactionsRepository.findByCustomerId(customerId);
		Map<String, Integer> monthlyPoints = new HashMap<>();
		int totalPoints = 0;
		for (Transactions transaction : transactionsList) {
			String month = transaction.getTransactionDate().getMonth().getDisplayName(TextStyle.SHORT,
					Locale.getDefault());
			int points = rewardServiceImplementation.calculateRewardPoints(transaction.getAmount());
			monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
			totalPoints += points;
		}
		CustomerRewardPointResponse rewardPointResponse = new CustomerRewardPointResponse(monthlyPoints, totalPoints);
		return rewardPointResponse;
	}
}
