/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 8, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.dto;

import java.sql.Date;


public class CustomerFeedBackDto {
	
	int cstrId;
	
	String cstrFeedBckCmnt;
	
	String cstrUserName;
	
    private String key;
    
    private Date commentDate;

	/**
	 * @return the cstrId
	 */
	public int getCstrId() {
		return cstrId;
	}

	/**
	 * @param cstrId the cstrId to set
	 */
	public void setCstrId(int cstrId) {
		this.cstrId = cstrId;
	}

	/**
	 * @return the cstrFeedBckCmnt
	 */
	public String getCstrFeedBckCmnt() {
		return cstrFeedBckCmnt;
	}

	/**
	 * @param cstrFeedBckCmnt the cstrFeedBckCmnt to set
	 */
	public void setCstrFeedBckCmnt(String cstrFeedBckCmnt) {
		this.cstrFeedBckCmnt = cstrFeedBckCmnt;
	}

	/**
	 * @return the cstrUserName
	 */
	public String getCstrUserName() {
		return cstrUserName;
	}

	/**
	 * @param cstrUserName the cstrUserName to set
	 */
	public void setCstrUserName(String cstrUserName) {
		this.cstrUserName = cstrUserName;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the commentDate
	 */
	public Date getCommentDate() {
		return commentDate;
	}

	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
	
	
	

}
