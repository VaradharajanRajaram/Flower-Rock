/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 9, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

/*Delivery=del
 * Resturant =res

*/
public class DeliveryDto {
	
	int deliveryDoingResId;
	
	int deliveryBoyId;
	
	String deliveryBoyName;

	Timestamp deliveryStartTime;
	 
	int deleiveryBillNo;
	
	int cstmrId;
	
	ArrayList<String> deliveryFoodList;

	/**
	 * @return the deliveryDoingResId
	 */
	public int getDeliveryDoingResId() {
		return deliveryDoingResId;
	}

	/**
	 * @param deliveryDoingResId the deliveryDoingResId to set
	 */
	public void setDeliveryDoingResId(int deliveryDoingResId) {
		this.deliveryDoingResId = deliveryDoingResId;
	}

	/**
	 * @return the cstmrId
	 */
	public int getCstmrId() {
		return cstmrId;
	}

	/**
	 * @param cstmrId the cstmrId to set
	 */
	public void setCstmrId(int cstmrId) {
		this.cstmrId = cstmrId;
	}

	/**
	 * @return the deliveryBoyName
	 */
	public String getDeliveryBoyName() {
		return deliveryBoyName;
	}

	/**
	 * @param deliveryBoyName the deliveryBoyName to set
	 */
	public void setDeliveryBoyName(String deliveryBoyName) {
		this.deliveryBoyName = deliveryBoyName;
	}

	/**
	 * @return the deliveryStartTime
	 */
	public Timestamp getDeliveryStartTime() {
		return deliveryStartTime;
	}

	/**
	 * @param deliveryStartTime the deliveryStartTime to set
	 */
	public void setDeliveryStartTime(Timestamp deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	/**
	 * @return the deleiveryBillNo
	 */
	public int getDeleiveryBillNo() {
		return deleiveryBillNo;
	}

	/**
	 * @param deleiveryBillNo the deleiveryBillNo to set
	 */
	public void setDeleiveryBillNo(int deleiveryBillNo) {
		this.deleiveryBillNo = deleiveryBillNo;
	}

	/**
	 * @return the deliveryFoodList
	 */
	public ArrayList<String> getDeliveryFoodList() {
		return deliveryFoodList;
	}

	/**
	 * @param deliveryFoodList the deliveryFoodList to set
	 */
	public void setDeliveryFoodList(ArrayList<String> deliveryFoodList) {
		this.deliveryFoodList = deliveryFoodList;
	}

	/**
	 * @return the deliveryBoyId
	 */
	public int getDeliveryBoyId() {
		return deliveryBoyId;
	}

	/**
	 * @param deliveryBoyId the deliveryBoyId to set
	 */
	public void setDeliveryBoyId(int deliveryBoyId) {
		this.deliveryBoyId = deliveryBoyId;
	}

	
}
