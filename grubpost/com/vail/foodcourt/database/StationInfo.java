package com.vail.foodcourt.database;

public class StationInfo {
	private String name;

	private int wrCount;

	public String getName() {
		return name;
	}

	public int getWrCount() {
		return wrCount;
	}

	public StationInfo(String name, int wrCount) {
		this.name = name;
		this.wrCount = wrCount;
	}
}