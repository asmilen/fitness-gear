package com.fitnessgear.model;

import java.io.Serializable;

import android.widget.ImageView;
import android.widget.TextView;

public class ExercisesItem  implements Serializable{

	private int exerciseID;
	private String exerciseName;
	private int exerciseType;
	private int muscleTarget;
	private int equipment;
	private float rating;
	private String img1;
	private String img2;
	private String description;
	private int sets;
	private int repsmin;
	private int repsmax;
	private int kg;
	private int rests;
	
	public ExercisesItem(int exerciseID, String exerciseName,
			int exerciseType, int muscleTarget, int equipment,
			float rating, String img1, String img2, String description,
			int sets, int repsmin,int repsmax, int kg, int rests) {
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

	public ExercisesItem(int exerciseID, String exerciseName,
			int exerciseType, int muscleTarget, int equipment,
			float rating, String img1, String img2, String description) {
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

	public int getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public int getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(int exerciseType) {
		this.exerciseType = exerciseType;
	}

	public int getMuscleTarget() {
		return muscleTarget;
	}

	public void setMuscleTarget(int muscleTarget) {
		this.muscleTarget = muscleTarget;
	}

	public int getEquipment() {
		return equipment;
	}

	public void setEquipment(int equipment) {
		this.equipment = equipment;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
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

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	

	public int getRepsmin() {
		return repsmin;
	}

	public void setRepsmin(int repsmin) {
		this.repsmin = repsmin;
	}

	public int getRepsmax() {
		return repsmax;
	}

	public void setRepsmax(int repsmax) {
		this.repsmax = repsmax;
	}

	public int getKg() {
		return kg;
	}

	public void setKg(int kg) {
		this.kg = kg;
	}

	public int getRests() {
		return rests;
	}

	public void setRests(int rests) {
		this.rests = rests;
	}

}
