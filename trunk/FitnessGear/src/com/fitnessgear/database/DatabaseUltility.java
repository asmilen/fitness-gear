package com.fitnessgear.database;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.fitnessgear.MainActivity;
import com.fitnessgear.model.LogExerciseItem;
import com.fitnessgear.model.LogExerciseList;

// class dung de define ten cac cot trong database va lay du lieu tu database
public class DatabaseUltility {

	private Bitmap bitmap;
	private ImageView img1;
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
	public static final String MuscleID = "MuscleId";
	public static final String MuscleName = "MuscleName";
	// Equipment Table
	public static final String EquipmentID = "EquipmentId";
	public static final String EquipmentName = "EquipmentName";
	// ExerciseType Table
	public static final String ExerciseTypeID = "ExerciseTypeId";
	public static final String ExerciseTypeName = "ExerciseTypeName";
	// Main_Goal table
	public static final String MainGoalID = "MainGoalID";
	public static final String MainGoalName = "MainGoalName";
	// Gender Table
	public static final String GenderID = "GenderID";
	public static final String GenderName = "GenderName";
	// Fitness Level Table
	public static final String FitnessLevelID = "FitnessLevelID";
	public static final String FitnessLevelName = "FitnessLevelName";

	// User Table
	public static final String UserID = "UserID";
	public static final String UserName = "UserName";
	public static final String DateOfBirth = "DateOfBirth";
	public static final String Weight = "Weight";
	public static final String BodyFat = "BodyFat";
	public static final String BMI = "BMI";
	public static final String Height = "Height";
	public static final String Waist = "Waist";
	public static final String Chest = "Chest";
	public static final String Arms = "Arms";
	public static final String Forearms = "Forearms";
	public static final String Shoulders = "Shoulders";
	public static final String Hips = "Hips";
	public static final String Thighs = "Thighs";
	public static final String Calves = "Calves";
	public static final String Neck = "Neck";
	// Log_Note Table
	public static final String dayID = "Day";
	public static final String NoteID = "NoteID";
	public static final String NoteName = "NoteName";
	public static final String NoteDetail = "NoteDetail";

	// Get data from table

	// Get data from column
	public static String GetColumnValue(Cursor cur, String ColumnName) {
		try {
			String temple = cur.getString(cur.getColumnIndex(ColumnName));
			if (temple == null) {
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
	public static void UpdateToLogExercise(LogExerciseList mylist)
			throws Exception {

		ArrayList<LogExerciseItem> list = mylist.getMyLogExerciseList();
		for (LogExerciseItem item : list) {
			SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();
			// Delete if exist
			db.execSQL("Delete from Log_Exercise Where ExerciseID = "
					+ item.getExerciseID() + " And Day =" + item.getDay()
					+ " And Sets = " + item.getSets());

			// Insert
			if (item.getReps() != 0 || item.getKgs() != 0
					|| item.getInterval() != 0) {
				ContentValues values = new ContentValues();
				values.put(DatabaseUltility.Day, item.getDay());
				values.put(DatabaseUltility.ExerciseID, item.getExerciseID());
				values.put(DatabaseUltility.Sets, item.getSets());
				values.put(DatabaseUltility.Reps, item.getReps());
				values.put(DatabaseUltility.Kg, item.getKgs());
				values.put(DatabaseUltility.Interval, item.getInterval());
				db.insert("Log_Exercise", null, values);
			}
		}

	}

	// Get List From Log_exercise
	public static ArrayList<LogExerciseItem> GetListFromLogExercise(String DayID) {
		ArrayList<LogExerciseItem> myListExerciseDetail = new ArrayList<LogExerciseItem>();
		String sql = "Select * " + "FROM Log_Exercise Where Day = '" + DayID
				+ "'";
		Cursor listExerciseCursor = MainActivity.db.rawQuery(sql, null);

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

	public static String getDayID() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1)
				+ "/" + c.get(Calendar.YEAR);
	}

	public static void UpdateToLogUser(ContentValues userContent) {
		// TODO Auto-generated method stub
		String dayID = getDayID();
		Cursor c = MainActivity.db.rawQuery("Select * from Log Where Day = '"
				+ dayID + "'", null);
		if (c.moveToNext()) {
			String UserID = DatabaseUltility.GetColumnValue(c,
					DatabaseUltility.UserID);
			MainActivity.db.update("User", userContent, "UserID=?",
					new String[] { UserID });
		} else {
			int ID = 1;
			Cursor c1 = MainActivity.db.rawQuery("Select * from User", null);
			while (c1.moveToNext())
				ID++;
			userContent.put("UserID", ID);
			SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();
			db.insert("User", null, userContent);

			ContentValues logContent = new ContentValues();
			logContent.put("Day", dayID);
			logContent.put("UserID", ID);
			db.insert("Log", null, logContent);
		}
	}

	public static String getMuscleName(int key) {
		// TODO Auto-generated method stub
		String name = "";
		Cursor c = MainActivity.db.rawQuery("Select * from Muscles Where MuscleID = " + key, null);
		if (c.moveToNext())
			name = GetColumnValue(c, MuscleName);
		return name;
	}

}


