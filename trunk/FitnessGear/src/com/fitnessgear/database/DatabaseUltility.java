package com.fitnessgear.database;

import android.database.Cursor;

// class dung de define ten cac cot trong database va lay du lieu tu database
public class DatabaseUltility {
	
	//My PLan
	public static final int MyplanId = 1;
	
	//Plan Table
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
	
	//Get data from table
	
	
	//Get data from column
	public static String GetColumnValue(Cursor cur, String ColumnName) {
		try {
			return cur.getString(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return "";
		}
	}
	
	
}
