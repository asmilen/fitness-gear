package com.fitnessgear.model;

public class GenderItem {
	private int genderID;
	private String genderName;

	public GenderItem(int genderID, String genderName) {
		super();
		this.genderID = genderID;
		this.genderName = genderName;
	}

	public int getGenderID() {
		return genderID;
	}

	public void setGenderID(int genderID) {
		this.genderID = genderID;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getGenderName();
	}
}
