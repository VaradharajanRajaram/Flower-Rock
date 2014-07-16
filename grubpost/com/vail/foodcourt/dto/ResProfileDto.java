/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 9, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.dto;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ResProfileDto {
	
	private String resName;
	
	private String resLocation;
	
	private JSONObject resBranch;
	
	private int resPhoneNo;
	
	private ArrayList<String> resType;
	
	private ArrayList<String> resFoodType;
	
	private int resSince;
	
	
	/**
	 * @return the resName
	 */
	public String getResName() {
		return resName;
	}
	/**
	 * @param resName the resName to set
	 */
	public void setResName(String resName) {
		this.resName = resName;
	}
	/**
	 * @return the resLocation
	 */
	public String getResLocation() {
		return resLocation;
	}
	/**
	 * @param resLocation the resLocation to set
	 */
	public void setResLocation(String resLocation) {
		this.resLocation = resLocation;
	}
	/**
	 * @return the resBranch
	 */
	public JSONObject getResBranch() {
		return resBranch;
	}
	/**
	 * @param resBranch the resBranch to set
	 */
	public void setResBranch(JSONObject resBranch) {
		this.resBranch = resBranch;
	}
	/**
	 * @return the resPhoneNo
	 */
	public int getResPhoneNo() {
		return resPhoneNo;
	}
	/**
	 * @param resPhoneNo the resPhoneNo to set
	 */
	public void setResPhoneNo(int resPhoneNo) {
		this.resPhoneNo = resPhoneNo;
	}
	/**
	 * @return the resType
	 */
	public ArrayList<String> getResType() {
		return resType;
	}
	/**
	 * @param resType the resType to set
	 */
	public void setResType(ArrayList<String> resType) {
		this.resType = resType;
	}
	/**
	 * @return the resFoodType
	 */
	public ArrayList<String> getResFoodType() {
		return resFoodType;
	}
	/**
	 * @param resFoodType the resFoodType to set
	 */
	public void setResFoodType(ArrayList<String> resFoodType) {
		this.resFoodType = resFoodType;
	}
	/**
	 * @return the resSince
	 */
	public int getResSince() {
		return resSince;
	}
	/**
	 * @param resSince the resSince to set
	 */
	public void setResSince(int resSince) {
		this.resSince = resSince;
	}
	/**
	 * @return the resRatings
	 */
	public String getResRatings() {
		return resRatings;
	}
	/**
	 * @param resRatings the resRatings to set
	 */
	public void setResRatings(String resRatings) {
		this.resRatings = resRatings;
	}
	String resRatings;
	
	
	
	
	

}
