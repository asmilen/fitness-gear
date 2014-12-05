package com.fitnessgear.database;

import java.util.ArrayList;
import java.util.Iterator;

import com.fitnessgear.MainActivity;
import com.fitnessgear.model.LogExerciseItem;
import com.fitnessgear.model.LogExerciseList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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
	public static final String Interval = "Interval";
	// Exercises Table
	public static final String ExerciseName = "ExerciseName";
	public static final String ExerciseType = "ExerciseType";
	public static final String MuscleTarget = "MuscleTarget";
	public static final String Equipment = "Equipment";
	public static final String Rating = "Rating";
	public static final String Image1 = "Image1";
	public static final String Image2 = "Image2";
	// Log_Exercise Table
	public static final String Reps = "Reps";
	public static final String Day = "Day";
	// Muscle Table
	public static final String MuscleID = "MuscleID";
	public static final String MuscleName = "MuscleName";
	// Equipment Table
	public static final String EquipmentID = "EquipmentID";
	public static final String EquipmentName = "EquipmentName";
	// ExerciseType Table
	public static final String ExerciseTypeID = "ExerciseTypeID";
	public static final String ExerciseTypeName = "ExerciseTypeName";
	//Main_Goal table
	public static final String MainGoalID = "MainGoalID";
	public static final String MainGoalName = "MainGoalName";
	//Gender Table
	public static final String GenderID = "GenderID";
	public static final String GenderName = "GenderName"; 
	//Fitness Level Table
	public static final String FitnessLevelID = "FitnessLevelID";
	public static final String FitnessLevelName = "FitnessLevelName";

	// Get data from table

	// Get data from column
	public static String GetColumnValue(Cursor cur, String ColumnName) {
		try {
			String temple = cur.getString(cur.getColumnIndex(ColumnName));
			if(temple == null){
				return "";
			}
			return cur.getString(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return "";
		}
	}

	public static int GetIntColumnValue(Cursor cur, String ColumnName) {
		try {
			return cur.getInt((cur.getColumnIndex(ColumnName)));
		} catch (Exception ex) {
			return 0;
		}
	}

	public static float GetFloatColumnValue(Cursor cur, String ColumnName) {
		try {
			return cur.getFloat((cur.getColumnIndex(ColumnName)));
		} catch (Exception ex) {
			return 0;
		}
	}

	// Update data to Log_exercise
	public static void UpdateToLogExercise(LogExerciseList mylist) {
		try {
			ArrayList<LogExerciseItem> list = mylist.getMyLogExerciseList();
			for (LogExerciseItem item : list) {
				SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();
				// Delete if exist
				db.execSQL("Delete from Log_Exercise Where ExerciseID = " + item.getExerciseID() + " And Day =" + item.getDay() +" And Sets = "+ item.getSets());
				
				// Insert
				String[] args = new String[] { item.getDay(),
						item.getExerciseID() + "", item.getSets() + "",
						item.getReps() + "", item.getKgs() + "",
						item.getInterval() + "" };
				String sql = "INSERT INTO Log_Exercise VALUES ( ?, ?, ?, ?, ?,?)";

			
				db.execSQL(sql, args);
			}
		} catch (Exception ex) {
			Log.e("error", ex.getMessage());
		}
	}

	// Get List From Log_exercise
	public static ArrayList<LogExerciseItem> GetListFromLogExercise(String DayID) {
		ArrayList<LogExerciseItem> myListExerciseDetail = new ArrayList<LogExerciseItem>();
		Cursor listExerciseCursor = MainActivity.db.rawQuery("Select * "
				+ "FROM Log_Exercise Where Day = " + DayID, null);

		while (listExerciseCursor.moveToNext()) {
			myListExerciseDetail.add(new LogExerciseItem(DayID,
					DatabaseUltility.GetIntColumnValue(listExerciseCursor,
							DatabaseUltility.ExerciseID), DatabaseUltility
							.GetIntColumnValue(listExerciseCursor,
									DatabaseUltility.Sets), DatabaseUltility
							.GetIntColumnValue(listExerciseCursor,
									DatabaseUltility.Reps), DatabaseUltility
							.GetIntColumnValue(listExerciseCursor,
									DatabaseUltility.Kg), DatabaseUltility
							.GetIntColumnValue(listExerciseCursor,
									DatabaseUltility.Interval)));
		}
		return myListExerciseDetail;

	}

	// Get total kg a day
	public static float GetTotalKgsDay(String DayID) {
		int total = 0;
		ArrayList<LogExerciseItem> mylist = GetListFromLogExercise(DayID);
		for (LogExerciseItem item : mylist)
			total += item.getKgs();
		return total;
	}
}
