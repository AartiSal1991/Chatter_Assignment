package com.chatter.com.java_assignment.service;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.chatter.com.java_assignment.entity.Transactions;

 

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})

public class TransactionRewardServiceTest {

	@Autowired
	private TransactionRewardService transactionRewardService;
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testSaveTransaction() {
		Transactions t = new Transactions();
		t.setId(13L);
		t.setAmount(200.00);
		t.setCustomer(customerService.getCustomerById(1L));
		t.setTransactionDate(new Date());
		Transactions newTransaction=transactionRewardService.createCustomerTransactions(t);
		assertNotNull(newTransaction.getId());
		assertEquals(t.getAmount(), newTransaction.getAmount());
	}
	 
	@Test
	public void testCalTotalRewardPointsByCustomerId() {
		Integer totalRewardPoints =0;
		Long custId =1L;
		assertNotNull(custId);
		totalRewardPoints= transactionRewardService.calculateTotalRewardPointsByCustomerId(custId);
		assertNotNull(totalRewardPoints);
		
	}

	@Test
	public void testCalMonthlyRewardPointsByCustomerId() {
		Integer monthlyRewardPoints =0;
		Long custId =1L;
		String yearMonth="2024-07";
		assertNotNull(custId);
		assertNotNull(yearMonth);
		monthlyRewardPoints=transactionRewardService.calculateMonthlyRewardPointsByCustomerId(custId, yearMonth);
		assertNotNull(monthlyRewardPoints);
	}

	@Test
	public void testCalTotalRewardPointsAllCustomer() {
		Map<String, Integer> custmerRewardMonthly = transactionRewardService.calculateTotalRewardPointsAllCustomer();
		assertNotNull(custmerRewardMonthly);
	}

	@Test
	public void testCalMonthlyRewardPointsAllCustomer() {
		String yearMonth="2024-07";
		assertNotNull(yearMonth);
		Map<String, Integer> custmerRewardTotal = transactionRewardService.calculateMonthlyRewardPointsAllCustomer(yearMonth);
		assertNotNull(custmerRewardTotal);
	}
	
	 
	
}
