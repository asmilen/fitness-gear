package com.fitnessgear.model;

import android.widget.ImageView;
import android.widget.TextView;

public class ListExercisesItem {

	private String exerciseID;
	private String exerciseName;
	private String exerciseType;
	private String muscleTarget;
	private String equipment;
	private String rating;
	private String img1;
	private String img2;
	private String description;
	private String sets;
	private String reps;
	private String kq;
	private String rests;
	
	public ListExercisesItem(String exerciseID, String exerciseName,
			String exerciseType, String muscleTarget, String equipment,
			String rating, String img1, String img2, String description,
			String sets, String reps, String kq, String rests) {
		super();
		this.exerciseID = exerciseID;
		this.exerciseName = exerciseName;
		this.exerciseType = exerciseType;
		this.muscleTarget = muscleTarget;
		this.equipment = equipment;
		this.rating = rating;
		this.img1 = img1;
		this.img2 = img2;
		this.description = description;
		this.sets = sets;
		this.reps = reps;
		this.kq = kq;
		this.rests = rests;
	}

	public String getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(String exerciseID) {
		this.exerciseID = exerciseID;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	public String getMuscleTarget() {
		return muscleTarget;
	}

	public void setMuscleTarget(String muscleTarget) {
		this.muscleTarget = muscleTarget;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSets() {
		return sets;
	}

	public void setSets(String sets) {
		this.sets = sets;
	}

	public String getReps() {
		return reps;
	}

	public void setReps(String reps) {
		this.reps = reps;
	}

	public String getKq() {
		return kq;
	}

	public void setKq(String kq) {
		this.kq = kq;
	}

	public String getRests() {
		return rests;
	}

	public void setRests(String rests) {
		this.rests = rests;
	}

}
