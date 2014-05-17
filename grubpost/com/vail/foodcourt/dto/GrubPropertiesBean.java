package com.vail.foodcourt.dto;

import java.sql.Date;

public class GrubPropertiesBean {

    private Integer grubId;

    private Integer grubsCustId;

    private String grubType;

    private Integer grubCost;

    private String grubImage;

    private Date grubRevealDate;

    private String grubsCustName;

    private String calorie;

    private String grubMenu;

    private String grubOffer;

    public Integer getGrubId() {
        return grubId;
    }

    public void setGrubId(Integer grubId) {
        this.grubId = grubId;
    }

    public Integer getGrubsCustId() {
        return grubsCustId;
    }

    public void setGrubsCustId(Integer grubsCustId) {
        this.grubsCustId = grubsCustId;
    }

    public String getGrubType() {
        return grubType;
    }

    public void setGrubType(String grubType) {
        this.grubType = grubType;
    }

    public Integer getGrubCost() {
        return grubCost;
    }

    public void setGrubCost(Integer grubCost) {
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

}
