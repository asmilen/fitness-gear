package com.fitnessgear.model;

public class ExerciseTypeItem {
	private int exerciseTypeID;
	private String exerciseTypeName;

	public ExerciseTypeItem(int exerciseTypeID, String exerciseTypeName) {
		super();
		this.exerciseTypeID = exerciseTypeID;
		this.exerciseTypeName = exerciseTypeName;
	}

	public int getExerciseTypeID() {
		return exerciseTypeID;
	}

	public void setExerciseTypeID(int exerciseTypeID) {
		this.exerciseTypeID = exerciseTypeID;
	}

	public String getExerciseTypeName() {
		return exerciseTypeName;
	}

	public void setExerciseTypeName(String exerciseTypeName) {
		this.exerciseTypeName = exerciseTypeName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getExerciseTypeName();
	}
}
