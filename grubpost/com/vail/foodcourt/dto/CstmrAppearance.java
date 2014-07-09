/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 9, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.dto;

import java.sql.Timestamp;


public class CstmrAppearance {
	
	private int customerId;
	
	private int custmerOrderedfoodId;
	
	private String custmerOrderedfoodName;
	
	private String custmerOrderedfoodRate;
	
	private String custmerOrderedLocation;
	
	private Timestamp foodOrderedDateTime;

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the custmerOrderedfoodId
	 */
	public int getCustmerOrderedfoodId() {
		return custmerOrderedfoodId;
	}

	/**
	 * @param custmerOrderedfoodId the custmerOrderedfoodId to set
	 */
	public void setCustmerOrderedfoodId(int custmerOrderedfoodId) {
		this.custmerOrderedfoodId = custmerOrderedfoodId;
	}

	/**
	 * @return the custmerOrderedfoodName
	 */
	public String getCustmerOrderedfoodName() {
		return custmerOrderedfoodName;
	}

	/**
	 * @param custmerOrderedfoodName the custmerOrderedfoodName to set
	 */
	public void setCustmerOrderedfoodName(String custmerOrderedfoodName) {
		this.custmerOrderedfoodName = custmerOrderedfoodName;
	}

	/**
	 * @return the custmerOrderedfoodRate
	 */
	public String getCustmerOrderedfoodRate() {
		return custmerOrderedfoodRate;
	}

	/**
	 * @param custmerOrderedfoodRate the custmerOrderedfoodRate to set
	 */
	public void setCustmerOrderedfoodRate(String custmerOrderedfoodRate) {
		this.custmerOrderedfoodRate = custmerOrderedfoodRate;
	}

	/**
	 * @return the custmerOrderedLocation
	 */
	public String getCustmerOrderedLocation() {
		return custmerOrderedLocation;
	}

	/**
	 * @param custmerOrderedLocation the custmerOrderedLocation to set
	 */
	public void setCustmerOrderedLocation(String custmerOrderedLocation) {
		this.custmerOrderedLocation = custmerOrderedLocation;
	}

	/**
	 * @return the foodOrderedDateTime
	 */
	public Timestamp getFoodOrderedDateTime() {
		return foodOrderedDateTime;
	}

	/**
	 * @param foodOrderedDateTime the foodOrderedDateTime to set
	 */
	public void setFoodOrderedDateTime(Timestamp foodOrderedDateTime) {
		this.foodOrderedDateTime = foodOrderedDateTime;
	}
	
	
	

}
