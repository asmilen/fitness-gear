package com.fitnessgear.model;

public class MuscleTargetItem {
	private int muscleID;
	private String muscleName;

	public MuscleTargetItem(int muscleID, String muscleName) {
		super();
		this.muscleID = muscleID;
		this.muscleName = muscleName;
	}

	public int getMuscleID() {
		return muscleID;
	}

	public void setMuscleID(int muscleID) {
		this.muscleID = muscleID;
	}

	public String getMuscleName() {
		return muscleName;
	}

	public void setMuscleName(String muscleName) {
		this.muscleName = muscleName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMuscleName();
	}
}
