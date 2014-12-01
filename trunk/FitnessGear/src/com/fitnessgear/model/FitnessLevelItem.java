package com.fitnessgear.model;

public class FitnessLevelItem {
	private int fitnessLevelID;
	private String fitnessLevelName;

	public FitnessLevelItem(int fitnessLevelID, String fitnessLevelName) {
		super();
		this.fitnessLevelID = fitnessLevelID;
		this.fitnessLevelName = fitnessLevelName;
	}

	public int getFitnessLevelID() {
		return fitnessLevelID;
	}

	public void setFitnessLevelID(int fitnessLevelID) {
		this.fitnessLevelID = fitnessLevelID;
	}

	public String getFitnessLevelName() {
		return fitnessLevelName;
	}

	public void setFitnessLevelName(String fitnessLevelName) {
		this.fitnessLevelName = fitnessLevelName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getFitnessLevelName();
	}
}
