package com.fitnessgear.model;

import java.io.Serializable;

public class LogExerciseItem implements Serializable{
	public int getInterval() {
		return Interval;
	}

	public void setInterval(int interval) {
		Interval = interval;
	}

	private String Day;
	private int ExerciseID;
	private int Sets;
	private int Reps;
	private int Kgs;
	private int Interval;

	public LogExerciseItem(int reps, int kgs, int interval) {
		super();
		Reps = reps;
		Kgs = kgs;
		Interval = interval;
	}

	public LogExerciseItem(String day, int exerciseID, int sets, int reps,
			int kgs, int interval) {
		super();
		Day = day;
		ExerciseID = exerciseID;
		Sets = sets;
		Reps = reps;
		Kgs = kgs;
		Interval = interval;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public int getExerciseID() {
		return ExerciseID;
	}

	public void setExerciseID(int exerciseID) {
		ExerciseID = exerciseID;
	}

	public int getSets() {
		return Sets;
	}

	public void setSets(int sets) {
		Sets = sets;
	}

	public int getReps() {
		return Reps;
	}

	public void setReps(int reps) {
		Reps = reps;
	}

	public int getKgs() {
		return Kgs;
	}

	public void setKgs(int kgs) {
		Kgs = kgs;
	}

}
