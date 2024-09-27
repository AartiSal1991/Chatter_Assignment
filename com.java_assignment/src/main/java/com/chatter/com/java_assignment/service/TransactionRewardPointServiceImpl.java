/**
 * This class is used to get the request asynchronous format which helps calling the thread to 
 * continue execute without blocking
 */
package com.chatter.com.java_assignment.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatter.com.java_assignment.entity.Customer;
import com.chatter.com.java_assignment.entity.Transactions;
import com.chatter.com.java_assignment.repository.CustomerRepository;
import com.chatter.com.java_assignment.repository.TransactionRepository; 
 
@Service
public class TransactionRewardPointServiceImpl implements TransactionRewardService{

	@Autowired
	private TransactionRepository transactionsRepository;

	 
	@Autowired
	private CustomerRepository customerRepository;
	 
	//Method for calculating reward point as per the given conditions
		@Override
		public Integer calculateRewardPointsCust(Double transactionAmount) {
			// TODO Auto-generated method stub
			int rewardPoints = 0;
			if(transactionAmount!=null) {
				if (transactionAmount >100)
				{
					Double remAmount=transactionAmount-100;
					rewardPoints += remAmount*2;
					rewardPoints+= 50 ;
				}
				else if(transactionAmount>=50 && transactionAmount<=100) {
					rewardPoints+= (transactionAmount-50);
				}
			}
			return rewardPoints;
		}

		//Calculating total reward points by Customer id
		@Override
		public Integer calculateTotalRewardPointsByCustomerId(Long customerId) {
			Integer totalRewardPoints =0;
		 	List<Transactions> allTransactions = transactionsRepository.findAll();
		 	
		 	List<Transactions> transactionListByCustomerId= allTransactions.stream()
		 			.filter(transaction -> transaction.getCustomer().getId()==customerId)
		 			.toList();
		 	//transactionListByCustomerId.forEach(transaction -> {totalRewardPoints += calculateRewardPoints(transaction.getAmount());});
		 	for(Transactions transaction :transactionListByCustomerId) {
		 		totalRewardPoints += calculateRewardPointsCust(transaction.getAmount());
		 	}
			return totalRewardPoints;
		}

		//Calculating monthly reward points by Customer id
		@Override
		public Integer calculateMonthlyRewardPointsByCustomerId(Long customerId,String yearMonth) {
			Integer monthlyRewardPoints =0;
			List<Transactions> allTransactions = transactionsRepository.findAll();
		 	List<Transactions> transactionListByCustomerId= allTransactions.stream()
		 			.filter(transaction -> transaction.getCustomer().getId()==customerId)
		 			.toList();
		 	
		 	YearMonth parsedYearMonth = YearMonth.parse(yearMonth);
		 	DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 	for(Transactions transaction :transactionListByCustomerId) {
		 		String[] newFormatedDate= transaction.getTransactionDate().toString().split(" ");
		 		
		 		if(LocalDate.parse(newFormatedDate[0],newPattern).getMonthValue() ==parsedYearMonth.getMonthValue() && LocalDate.parse(newFormatedDate[0],newPattern).getYear()==parsedYearMonth.getYear())
		 		{
		 			monthlyRewardPoints +=calculateRewardPointsCust(transaction.getAmount());
		 		}
		 	}
			return monthlyRewardPoints;
		}

		
		//Calculating Total reward points for all Customers
		@Override
		public Map<String, Integer> calculateTotalRewardPointsAllCustomer() {
			Map<String, Integer> custmerRewardTotal = new HashMap<String, Integer>();
			List<Transactions> allTransactions = transactionsRepository.findAll();
			for(Transactions transaction :allTransactions) {
			Customer customer=customerRepository.findById(transaction.getCustomer().getId()).get();	
			custmerRewardTotal.put(customer.getCustomer_name(), calculateTotalRewardPointsByCustomerId(transaction.getCustomer().getId()));
			}
			return custmerRewardTotal;
		}

		//Calculating Monthly reward points for all Customers
		@Override
		public Map<String, Integer> calculateMonthlyRewardPointsAllCustomer(String yearMonth) {
			List<Transactions> allTransactions = transactionsRepository.findAll();
			Map<String, Integer> custmerRewardMonthly = new HashMap<String, Integer>();
			for(Transactions transaction :allTransactions) {
				Customer customer=customerRepository.findById(transaction.getCustomer().getId()).get();	
				custmerRewardMonthly.put(customer.getCustomer_name()+":"+yearMonth, calculateMonthlyRewardPointsByCustomerId(transaction.getCustomer().getId(),yearMonth));
				}
			return custmerRewardMonthly;
		}
 
		 
		@Override
		public Transactions createCustomerTransactions(Transactions transaction) {
			transactionsRepository.save(transaction);
			return transaction;
		}

		 
}
