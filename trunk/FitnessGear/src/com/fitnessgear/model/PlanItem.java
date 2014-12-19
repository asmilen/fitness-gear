package com.fitnessgear.model;

public class PlanItem {
	private int PlanID;
	private String PlanName;
	private int MainGoalID;
	private int GenderID;
	private int FitnessLevelID;
	private String CreatedBy;
	private String DateCreated;
	private int TotalWeeks;
	private float AveDay;
	private float AveWorkoutTime;
	private int TotalCardioTime;
	private int TotalTimeAWeek;

	public PlanItem(int planID, String planName, int mainGoalID, int genderID,
			int fitnessLevelID, String createdBy,
			String dateCreated, int totalWeeks, float aveDay,
			float aveWorkoutTime, int totalCardioTime, int totalTimeAWeek) {
		super();
		PlanID = planID;
		PlanName = planName;
		MainGoalID = mainGoalID;
		GenderID = genderID;
		FitnessLevelID = fitnessLevelID;
		CreatedBy = createdBy;
		DateCreated = dateCreated;
		TotalWeeks = totalWeeks;
		AveDay = aveDay;
		AveWorkoutTime = aveWorkoutTime;
		TotalCardioTime = totalCardioTime;
		TotalTimeAWeek = totalTimeAWeek;
	}

	public int getPlanID() {
		return PlanID;
	}

	public void setPlanID(int planID) {
		PlanID = planID;
	}

	public String getPlanName() {
		return PlanName;
	}

	public void setPlanName(String planName) {
		PlanName = planName;
	}

	public int getMainGoalID() {
		return MainGoalID;
	}

	public void setMainGoalID(int mainGoalID) {
		MainGoalID = mainGoalID;
	}


	public int getGenderID() {
		return GenderID;
	}

	public void setGenderID(int genderID) {
		GenderID = genderID;
	}

	public int getFitnessLevelID() {
		return FitnessLevelID;
	}

	public void setFitnessLevelID(int fitnessLevelID) {
		FitnessLevelID = fitnessLevelID;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public String getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}

	public int getTotalWeeks() {
		return TotalWeeks;
	}

	public void setTotalWeeks(int totalWeeks) {
		TotalWeeks = totalWeeks;
	}

	public float getAveDay() {
		return AveDay;
	}

	public void setAveDay(float aveDay) {
		AveDay = aveDay;
	}

	public float getAveWorkoutTime() {
		return AveWorkoutTime;
	}

	public void setAveWorkoutTime(float aveWorkoutTime) {
		AveWorkoutTime = aveWorkoutTime;
	}

	public int getTotalCardioTime() {
		return TotalCardioTime;
	}

	public void setTotalCardioTime(int totalCardioTime) {
		TotalCardioTime = totalCardioTime;
	}

	public int getTotalTimeAWeek() {
		return TotalTimeAWeek;
	}

	public void setTotalTimeAWeek(int totalTimeAWeek) {
		TotalTimeAWeek = totalTimeAWeek;
	}

}
