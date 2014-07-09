/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 9, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.dto;

public class CstmrAddress {

	private String Name;

	private int phoneNumber;

	private String streetName;

	private String landMark;

	private String cityName;// Porur,Mugapair,Vanagaram,TNagar..

	private int doorNumber;

	private String district;

	private String latitude_landidude;

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the landMark
	 */
	public String getLandMark() {
		return landMark;
	}

	/**
	 * @param landMark
	 *            the landMark to set
	 */
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the doorNumber
	 */
	public int getDoorNumber() {
		return doorNumber;
	}

	/**
	 * @param doorNumber
	 *            the doorNumber to set
	 */
	public void setDoorNumber(int doorNumber) {
		this.doorNumber = doorNumber;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the latitude_landidude
	 */
	public String getLatitude_landidude() {
		return latitude_landidude;
	}

	/**
	 * @param latitude_landidude
	 *            the latitude_landidude to set
	 */
	public void setLatitude_landidude(String latitude_landidude) {
		this.latitude_landidude = latitude_landidude;
	}

}
