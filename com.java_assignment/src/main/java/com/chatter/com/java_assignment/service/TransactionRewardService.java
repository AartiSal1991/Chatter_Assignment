/**
 * This is the interface used to call the implementation logic for the service class 
 */
package com.chatter.com.java_assignment.service;

import java.util.Map;

import com.chatter.com.java_assignment.entity.Transactions;

public interface TransactionRewardService {

	/**
	 * contains the logic to calculate the reward points
	 * 
	 */
 
	public Integer calculateRewardPointsCust(Double transactionAmount);
	public Integer calculateTotalRewardPointsByCustomerId(Long customerId);
	public Integer calculateMonthlyRewardPointsByCustomerId(Long customerId,String yearMonth);
	public Map<String,Integer> calculateTotalRewardPointsAllCustomer();
	public Map<String,Integer> calculateMonthlyRewardPointsAllCustomer(String yearMonth);
	Transactions createCustomerTransactions(Transactions transaction);

}
