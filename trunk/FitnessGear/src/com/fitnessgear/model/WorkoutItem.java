package com.fitnessgear.model;

public class WorkoutItem {

	private String header;
	private String workoutID;
	private String workoutName;
	private String description;
	private String totalWorkoutTime;
	private String totalCardioTime;
	private String totalExercises;
	private String totalSets;

	
	
	public WorkoutItem(String header) {
		super();
		this.header = header;
	}

	public WorkoutItem(String workoutID, String workoutName,
			String description, String totalWorkoutTime,
			String totalCardioTime, String totalExercises, String totalSets) {
		super();
		this.workoutID = workoutID;
		this.workoutName = workoutName;
		this.description = description;
		this.totalWorkoutTime = totalWorkoutTime;
		this.totalCardioTime = totalCardioTime;
		this.totalExercises = totalExercises;
		this.totalSets = totalSets;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getWorkoutID() {
		return workoutID;
	}

	public void setWorkoutID(String workoutID) {
		this.workoutID = workoutID;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTotalWorkoutTime() {
		return totalWorkoutTime;
	}

	public void setTotalWorkoutTime(String totalWorkoutTime) {
		this.totalWorkoutTime = totalWorkoutTime;
	}

	public String getTotalCardioTime() {
		return totalCardioTime;
	}

	public void setTotalCardioTime(String totalCardioTime) {
		this.totalCardioTime = totalCardioTime;
	}

	public String getTotalExercises() {
		return totalExercises;
	}

	public void setTotalExercises(String totalExercises) {
		this.totalExercises = totalExercises;
	}

	public String getTotalSets() {
		return totalSets;
	}

	public void setTotalSets(String totalSets) {
		this.totalSets = totalSets;
	}

}
