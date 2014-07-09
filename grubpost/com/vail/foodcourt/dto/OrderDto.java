/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 9, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.dto;

import java.sql.Timestamp;

public class OrderDto {
	
	
	//Based on the information of user request we have to fill the DTO
	/*CUSTOMER-custmr
	 * ORDER-odr
	 * DELIVERY-del
	 */
	private int odringcstmrId;
	
	private int odrId;
	
	private int odrFrom;
	
	private String cstmrDelAddress;
	
	private String orderFoodName;
	
	private Timestamp orderDateTime;
	
	private String orderEmailId;
	
	private boolean isExistingCustomer;

	/**
	 * @return the odringcstmrId
	 */
	public int getOdringcstmrId() {
		return odringcstmrId;
	}

	/**
	 * @param odringcstmrId the odringcstmrId to set
	 */
	public void setOdringcstmrId(int odringcstmrId) {
		this.odringcstmrId = odringcstmrId;
	}

	/**
	 * @return the odrId
	 */
	public int getOdrId() {
		return odrId;
	}

	/**
	 * @param odrId the odrId to set
	 */
	public void setOdrId(int odrId) {
		this.odrId = odrId;
	}

	/**
	 * @return the odrFrom
	 */
	public int getOdrFrom() {
		return odrFrom;
	}

	/**
	 * @param odrFrom the odrFrom to set
	 */
	public void setOdrFrom(int odrFrom) {
		this.odrFrom = odrFrom;
	}

	/**
	 * @return the cstmrDelAddress
	 */
	public String getCstmrDelAddress() {
		return cstmrDelAddress;
	}

	/**
	 * @param cstmrDelAddress the cstmrDelAddress to set
	 */
	public void setCstmrDelAddress(String cstmrDelAddress) {
		this.cstmrDelAddress = cstmrDelAddress;
	}

	/**
	 * @return the orderFoodName
	 */
	public String getOrderFoodName() {
		return orderFoodName;
	}

	/**
	 * @param orderFoodName the orderFoodName to set
	 */
	public void setOrderFoodName(String orderFoodName) {
		this.orderFoodName = orderFoodName;
	}

	/**
	 * @return the orderDateTime
	 */
	public Timestamp getOrderDateTime() {
		return orderDateTime;
	}

	/**
	 * @param orderDateTime the orderDateTime to set
	 */
	public void setOrderDateTime(Timestamp orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	/**
	 * @return the orderEmailId
	 */
	public String getOrderEmailId() {
		return orderEmailId;
	}

	/**
	 * @param orderEmailId the orderEmailId to set
	 */
	public void setOrderEmailId(String orderEmailId) {
		this.orderEmailId = orderEmailId;
	}

	/**
	 * @return the isExistingCustomer
	 */
	public boolean isExistingCustomer() {
		return isExistingCustomer;
	}

	/**
	 * @param isExistingCustomer the isExistingCustomer to set
	 */
	public void setExistingCustomer(boolean isExistingCustomer) {
		this.isExistingCustomer = isExistingCustomer;
	}
	

}
