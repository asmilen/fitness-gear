package com.fitnessgear.model;

public class GridItem {

	private String header;
	private String workoutID;
	private String txtTime;
	private String txtExercise;

	public GridItem() {
		// TODO Auto-generated constructor stub
	}

	public GridItem(String header) {
		super();
		this.header = header;
	}

	public GridItem(String workoutID, String txtTime, String txtExercise ) {
		this.workoutID = workoutID;
		this.txtTime = txtTime;
		this.txtExercise = txtExercise;
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

	public String getTxtTime() {
		return txtTime;
	}

	public void setTxtTime(String txtTime) {
		this.txtTime = txtTime;
	}

	public String getTxtExercise() {
		return txtExercise;
	}

	public void setTxtExercise(String txtExercise) {
		this.txtExercise = txtExercise;
	}

}
