/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 9, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.dto;

import java.sql.Date;

public class ResFoodDto {

	private int grubId;
	
	private int grubResId;
	
    private int grubsCustId;

    private String grubType;

    private int grubCost;

    private String grubImage;

    private Date grubRevealDate;

    private String grubsCustName;

    private String calorie;

    private String grubMenu;

    public int getGrubId() {
		return grubId;
	}

	public void setGrubId(int grubId) {
		this.grubId = grubId;
	}

	public int getGrubResId() {
		return grubResId;
	}

	public void setGrubResId(int grubResId) {
		this.grubResId = grubResId;
	}

	public int getGrubsCustId() {
		return grubsCustId;
	}

	public void setGrubsCustId(int grubsCustId) {
		this.grubsCustId = grubsCustId;
	}

	public String getGrubType() {
		return grubType;
	}

	public void setGrubType(String grubType) {
		this.grubType = grubType;
	}

	public int getGrubCost() {
		return grubCost;
	}

	public void setGrubCost(int grubCost) {
		this.grubCost = grubCost;
	}

	public String getGrubImage() {
		return grubImage;
	}

	public void setGrubImage(String grubImage) {
		this.grubImage = grubImage;
	}

	public Date getGrubRevealDate() {
		return grubRevealDate;
	}

	public void setGrubRevealDate(Date grubRevealDate) {
		this.grubRevealDate = grubRevealDate;
	}

	public String getGrubsCustName() {
		return grubsCustName;
	}

	public void setGrubsCustName(String grubsCustName) {
		this.grubsCustName = grubsCustName;
	}

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getGrubMenu() {
		return grubMenu;
	}

	public void setGrubMenu(String grubMenu) {
		this.grubMenu = grubMenu;
	}

	public String getGrubOffer() {
		return grubOffer;
	}

	public void setGrubOffer(String grubOffer) {
		this.grubOffer = grubOffer;
	}

	private String grubOffer;


}
