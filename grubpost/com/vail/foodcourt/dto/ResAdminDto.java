/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 9, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.dto;

import java.sql.Date;

public class ResAdminDto {

	private Integer ResId;
	
	private Integer ResLoginId;
	
	private Date ResAdminCheckOut;
	
	private Date ResAdminCheckIn;
	
	private Integer ResPassWord;
	
	private String ResUrlId;

	public Integer getResId() {
		return ResId;
	}

	public void setResId(Integer resId) {
		ResId = resId;
	}

	public Integer getResLoginId() {
		return ResLoginId;
	}

	public void setResLoginId(Integer resLoginId) {
		ResLoginId = resLoginId;
	}

	public Date getResAdminCheckOut() {
		return ResAdminCheckOut;
	}

	public void setResAdminCheckOut(Date resAdminCheckOut) {
		ResAdminCheckOut = resAdminCheckOut;
	}

	public Date getResAdminCheckIn() {
		return ResAdminCheckIn;
	}

	public void setResAdminCheckIn(Date resAdminCheckIn) {
		ResAdminCheckIn = resAdminCheckIn;
	}

	public Integer getResPassWord() {
		return ResPassWord;
	}

	public void setResPassWord(Integer resPassWord) {
		ResPassWord = resPassWord;
	}

	public String getResUrlId() {
		return ResUrlId;
	}

	public void setResUrlId(String resUrlId) {
		ResUrlId = resUrlId;
	}

		
}
