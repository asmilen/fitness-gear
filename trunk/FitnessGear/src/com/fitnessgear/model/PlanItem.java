package com.fitnessgear.model;

public class PlanItem {
	private String PlanID;
	private String PlanName;
	private String MainGoal;
	private String Gender;
	private String FitnessLevel;
	private String CreatedBy;
	private String DateCreated;
	private String TotalWeeks;
	private String AveDay;
	private String AveWorkoutTime;
	private String TotalCardioTime;
	private String TotalTimeAWeek;

	public PlanItem(String planID, String planName, String mainGoal,
			String gender, String fitnessLevel, String createdBy,
			String dateCreated, String totalWeeks, String aveDay,
			String aveWorkoutTime, String totalCardioTime, String totalTimeAWeek) {
		super();
		PlanID = planID;
		PlanName = planName;
		MainGoal = mainGoal;
		Gender = gender;
		FitnessLevel = fitnessLevel;
		CreatedBy = createdBy;
		DateCreated = dateCreated;
		TotalWeeks = totalWeeks;
		AveDay = aveDay;
		AveWorkoutTime = aveWorkoutTime;
		TotalCardioTime = totalCardioTime;
		TotalTimeAWeek = totalTimeAWeek;
	}

	public String getPlanID() {
		return PlanID;
	}

	public void setPlanID(String planID) {
		PlanID = planID;
	}

	public String getPlanName() {
		return PlanName;
	}

	public void setPlanName(String planName) {
		PlanName = planName;
	}

	public String getMainGoal() {
		return MainGoal;
	}

	public void setMainGoal(String mainGoal) {
		MainGoal = mainGoal;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getFitnessLevel() {
		return FitnessLevel;
	}

	public void setFitnessLevel(String fitnessLevel) {
		FitnessLevel = fitnessLevel;
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

	public String getTotalWeeks() {
		return TotalWeeks;
	}

	public void setTotalWeeks(String totalWeeks) {
		TotalWeeks = totalWeeks;
	}

	public String getAveDay() {
		return AveDay;
	}

	public void setAveDay(String aveDay) {
		AveDay = aveDay;
	}

	public String getAveWorkoutTime() {
		return AveWorkoutTime;
	}

	public void setAveWorkoutTime(String aveWorkoutTime) {
		AveWorkoutTime = aveWorkoutTime;
	}

	public String getTotalCardioTime() {
		return TotalCardioTime;
	}

	public void setTotalCardioTime(String totalCardioTime) {
		TotalCardioTime = totalCardioTime;
	}

	public String getTotalTimeAWeek() {
		return TotalTimeAWeek;
	}

	public void setTotalTimeAWeek(String totalTimeAWeek) {
		TotalTimeAWeek = totalTimeAWeek;
	}

}
