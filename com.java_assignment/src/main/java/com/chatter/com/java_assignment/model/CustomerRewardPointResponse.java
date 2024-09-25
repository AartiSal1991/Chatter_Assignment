/**
 * This class is used to handle the response which is coming in the getRequest
 */
package com.chatter.com.java_assignment.model;

import java.util.Map;
 
public class CustomerRewardPointResponse {

	/** The monthlyPoints*/
	private Map<String, Integer> monthlyPoints;
	
	/** The totalPoints */
	private int totalPoints;

	/**
	 * @param monthlyPoints
	 * @param totalPoints
	 */
	public CustomerRewardPointResponse(Map<String, Integer> monthlyPoints, int totalPoints) {
		this.monthlyPoints = monthlyPoints;
		this.totalPoints = totalPoints;
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
