package com.example.fitnessgear.model;

public class GridItem {

	private String txtTime;
	private String txtExercise;
	
	public GridItem() {
		// TODO Auto-generated constructor stub
	}

	public GridItem(String txtTime, String txtExercise) {
		this.txtTime = txtTime;
		this.txtExercise = txtExercise;
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
