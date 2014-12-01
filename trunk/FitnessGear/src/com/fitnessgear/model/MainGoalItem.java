package com.fitnessgear.model;

public class MainGoalItem {
	private int mainGoalID;
	private String mainGoalName;

	public MainGoalItem(int mainGoalID, String mainGoalName) {
		super();
		this.mainGoalID = mainGoalID;
		this.mainGoalName = mainGoalName;
	}

	public int getMainGoalID() {
		return mainGoalID;
	}

	public void setMainGoalID(int mainGoalID) {
		this.mainGoalID = mainGoalID;
	}

	public String getMainGoalName() {
		return mainGoalName;
	}

	public void setMainGoalName(String mainGoalName) {
		this.mainGoalName = mainGoalName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMainGoalName();
	}
}
