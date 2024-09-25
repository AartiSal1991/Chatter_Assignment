/**
 * This is the interface used to call the implementation logic for the service class 
 */
package com.chatter.com.java_assignment.service;

 
public interface TransactionRewardService {

	/**
	 * contains the logic to calculate the reward points
	 * 
	 */
	public int calculateRewardPoints(Double amount);

}
