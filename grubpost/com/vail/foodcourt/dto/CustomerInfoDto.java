package com.vail.foodcourt.dto;

import java.util.ArrayList;


public class CustomerInfoDto {

	
	private Integer cstmrId;
	
	private String cstmrName;
	
	private int cstmrPhoneNo;
	
	private String cstmrLoginSrc;
	
	private String cstmrFbId;
	
	private String cstmrGmailId;
	
	private ArrayList<CstmrAddress> cstmrAddrs;

	/**
	 * @return the cstmrId
	 */
	public Integer getCstmrId() {
		return cstmrId;
	}

	/**
	 * @param cstmrId the cstmrId to set
	 */
	public void setCstmrId(Integer cstmrId) {
		this.cstmrId = cstmrId;
	}

	/**
	 * @return the cstmrName
	 */
	public String getCstmrName() {
		return cstmrName;
	}

	/**
	 * @param cstmrName the cstmrName to set
	 */
	public void setCstmrName(String cstmrName) {
		this.cstmrName = cstmrName;
	}

	/**
	 * @return the cstmrPhoneNo
	 */
	public int getCstmrPhoneNo() {
		return cstmrPhoneNo;
	}

	/**
	 * @param cstmrPhoneNo the cstmrPhoneNo to set
	 */
	public void setCstmrPhoneNo(int cstmrPhoneNo) {
		this.cstmrPhoneNo = cstmrPhoneNo;
	}

	/**
	 * @return the cstmrLoginSrc
	 */
	public String getCstmrLoginSrc() {
		return cstmrLoginSrc;
	}

	/**
	 * @param cstmrLoginSrc the cstmrLoginSrc to set
	 */
	public void setCstmrLoginSrc(String cstmrLoginSrc) {
		this.cstmrLoginSrc = cstmrLoginSrc;
	}

	/**
	 * @return the cstmrFbId
	 */
	public String getCstmrFbId() {
		return cstmrFbId;
	}

	/**
	 * @param cstmrFbId the cstmrFbId to set
	 */
	public void setCstmrFbId(String cstmrFbId) {
		this.cstmrFbId = cstmrFbId;
	}

	/**
	 * @return the cstmrGmailId
	 */
	public String getCstmrGmailId() {
		return cstmrGmailId;
	}

	/**
	 * @param cstmrGmailId the cstmrGmailId to set
	 */
	public void setCstmrGmailId(String cstmrGmailId) {
		this.cstmrGmailId = cstmrGmailId;
	}

	/**
	 * @return the cstmrAddrs
	 */
	public ArrayList<CstmrAddress> getCstmrAddrs() {
		return cstmrAddrs;
	}

	/**
	 * @param cstmrAddrs the cstmrAddrs to set
	 */
	public void setCstmrAddrs(ArrayList<CstmrAddress> cstmrAddrs) {
		this.cstmrAddrs = cstmrAddrs;
	}
	
	
	
	
	
}
