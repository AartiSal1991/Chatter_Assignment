/**
 * This class is created to fetch all the response and display they with respect to customerIds
 */
package com.chatter.com.java_assignment.model;

import java.util.Map;

 
public class TransactionRewardPointsForAllCustomer {

	private Long customerId;
	private Map<String, Integer> monthlyPoints;
	private int totalPoints;

	/**
	 * @param customerId
	 * @param monthlyPoints
	 * @param totalPoints
	 */
	public TransactionRewardPointsForAllCustomer(Long customerId, Map<String, Integer> monthlyPoints, int totalPoints) {
		this.customerId = customerId;
		this.monthlyPoints = monthlyPoints;
		this.totalPoints = totalPoints;
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the monthlyPoints
	 */
	public Map<String, Integer> getMonthlyPoints() {
		return monthlyPoints;
	}

	/**
	 * @param monthlyPoints the monthlyPoints to set
	 */
	public void setMonthlyPoints(Map<String, Integer> monthlyPoints) {
		this.monthlyPoints = monthlyPoints;
	}

	/**
	 * @return the totalPoints
	 */
	public int getTotalPoints() {
		return totalPoints;
	}

	/**
	 * @param totalPoints the totalPoints to set
	 */
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

}
