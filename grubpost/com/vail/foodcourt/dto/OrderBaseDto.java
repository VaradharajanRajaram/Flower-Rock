/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 9, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.dto;

import java.util.ArrayList;


public class OrderBaseDto {
	private int ordrTakenResId;

	private String ordrTakenResName;
	
	private int orderGivingCstmrId;
	
	private String orderGivingCstmrName;
	

	private ArrayList<OrderDto> orderDto;

	private ArrayList<CstmrAppearance> cstmrAppearance;

	/**
	 * @return the orderDto
	 */
	public ArrayList<OrderDto> getOrderDto() {
		return orderDto;
	}

	/**
	 * @param orderDto
	 *            the orderDto to set
	 */
	public void setOrderDto(ArrayList<OrderDto> orderDto) {
		this.orderDto = orderDto;
	}

	/**
	 * @return the cstmrAppearance
	 */
	public ArrayList<CstmrAppearance> getCstmrAppearance() {
		return cstmrAppearance;
	}

	/**
	 * @param cstmrAppearance
	 *            the cstmrAppearance to set
	 */
	public void setCstmrAppearance(ArrayList<CstmrAppearance> cstmrAppearance) {
		this.cstmrAppearance = cstmrAppearance;
	}

	/**
	 * @return the ordrTakenResId
	 */
	public int getOrdrTakenResId() {
		return ordrTakenResId;
	}

	/**
	 * @param ordrTakenResId the ordrTakenResId to set
	 */
	public void setOrdrTakenResId(int ordrTakenResId) {
		this.ordrTakenResId = ordrTakenResId;
	}

	/**
	 * @return the ordrTakenResName
	 */
	public String getOrdrTakenResName() {
		return ordrTakenResName;
	}

	/**
	 * @param ordrTakenResName the ordrTakenResName to set
	 */
	public void setOrdrTakenResName(String ordrTakenResName) {
		this.ordrTakenResName = ordrTakenResName;
	}

	/**
	 * @return the orderGivingCstmrId
	 */
	public int getOrderGivingCstmrId() {
		return orderGivingCstmrId;
	}

	/**
	 * @param orderGivingCstmrId the orderGivingCstmrId to set
	 */
	public void setOrderGivingCstmrId(int orderGivingCstmrId) {
		this.orderGivingCstmrId = orderGivingCstmrId;
	}

	/**
	 * @return the orderGivingCstmrName
	 */
	public String getOrderGivingCstmrName() {
		return orderGivingCstmrName;
	}

	/**
	 * @param orderGivingCstmrName the orderGivingCstmrName to set
	 */
	public void setOrderGivingCstmrName(String orderGivingCstmrName) {
		this.orderGivingCstmrName = orderGivingCstmrName;
	}

	}
