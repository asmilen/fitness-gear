package com.fitnessgear.database;

import android.database.Cursor;

// class dung de define ten cac cot trong database va lay du lieu tu database
public class DatabaseUltility {

	// My PLan
	public static final int MyplanId = 1;

	// Plan Table
	public static final String Plan = "Plan";
	public static final String PlanID = "PlanID";
	public static final String PlanName = "PlanName";
	public static final String CreatedBy = "CreatedBy";
	public static final String Gender = "Gender";
	public static final String MainGoal = "MainGoal";
	public static final String FitnessLevel = "FitnessLevel";
	public static final String DateCreated = "DateCreated";
	public static final String TotalWeeks = "TotalWeeks";
	public static final String AveDay = "AveDay";
	public static final String AveWorkoutTime = "AveWorkoutTime";
	public static final String TotalTimeAWeek = "TotalTimeAWeek";
	public static final String TotalCardioTime = "TotalCardioTime";
	
	// Workout Table
	public static final String WorkoutID = "WorkoutID";
	public static final String WorkoutName = "WorkoutName";
	public static final String TotalWorkoutTime = "TotalWorkoutTime";
	public static final String TotalExercises = "TotalExercises";
	public static final String TotalSets = "TotalSets";
	public static final String AvaImage = "AvaImage";
	public static final String Description = "Description";

	// Workout_Exercise Table
	public static final String ExerciseID = "ExerciseID";
	public static final String Sets = "Sets";
	public static final String RepsMin = "RepsMin";
	public static final String RepsMax = "RepsMax";
	public static final String Kg = "Kg";
	public static final String Rests = "Rests";

	// Exercises Table
	public static final String ExerciseName = "ExerciseName";
	public static final String ExerciseType = "ExerciseType";
	public static final String MuscleTarget = "MuscleTarget";
	public static final String Equipment = "Equipment";
	public static final String Rating = "Rating";
	public static final String Image1 = "Image1";
	public static final String Image2 = "Image2";

	
	//Log_Exercise Table
	public static final String Reps = "Reps";
	public static final String Day = "Day";
	

	//Muscle Table
	public static final String MuscleID = "MuscleID";
	public static final String MuscleName = "MuscleName";
	//Equipment Table
	public static final String EquipmentID = "EquipmentID";
	public static final String EquipmentName = "EquipmentName";
	//ExerciseType Table
	public static final String ExerciseTypeID = "ExerciseTypeID";
	public static final String ExerciseTypeName = "ExerciseTypeName";
	

	// Get data from table

	// Get data from column
	public static String GetColumnValue(Cursor cur, String ColumnName) {
		try {
			return cur.getString(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return "";
		}
	}

}
