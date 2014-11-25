package com.fitnessgear.model;

import java.io.Serializable;

import android.widget.ImageView;
import android.widget.TextView;

public class ListExercisesItem  implements Serializable{

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
	private String repsmin;
	private String repsmax;
	private String kg;
	private String rests;
	
	public ListExercisesItem(String exerciseID, String exerciseName,
			String exerciseType, String muscleTarget, String equipment,
			String rating, String img1, String img2, String description,
			String sets, String repsmin,String repsmax, String kg, String rests) {
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
		this.repsmin = repsmin;
		this.repsmax = repsmax;
		this.kg = kg;
		this.rests = rests;
	}

	public ListExercisesItem(String exerciseID, String exerciseName,
			String exerciseType, String muscleTarget, String equipment,
			String rating, String img1, String img2, String description) {
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

	

	public String getRepsmin() {
		return repsmin;
	}

	public void setRepsmin(String repsmin) {
		this.repsmin = repsmin;
	}

	public String getRepsmax() {
		return repsmax;
	}

	public void setRepsmax(String repsmax) {
		this.repsmax = repsmax;
	}

	public String getKg() {
		return kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}

	public String getRests() {
		return rests;
	}

	public void setRests(String rests) {
		this.rests = rests;
	}

}
