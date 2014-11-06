package com.fitnessgear.database;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CreateDatabase extends SQLiteOpenHelper {
	
	public static String DATABASE_NAME = "fitnessGear.db";
	public static int VERSION = 1;
	public static String CREATE_PLAN_TABLE = "CREATE TABLE Plan (	" +
			"PlanID	INTEGER PRIMARY KEY AUTOINCREMENT,	" +
			"PlanName	TEXT NOT NULL,	" +
			"MainGoal	TEXT,	" +
			"Gender	TEXT,	" +
			"FitnessLevel	TEXT,	" +
			"CreatedBy	TEXT,	" +
			"DateCreated	TEXT," +
			"TotalWeeks	INTEGER NOT NULL,	" +
			"AveDay	REAL NOT NULL,	" +
			"AveWorkoutTime	REAL NOT NULL," +
			"TotalCardioTime	INTEGER NOT NULL,	" +
			"TotalTimeAWeek	INTEGER NOT NULL);";
	public static String INSERT_INTO_PLAN = "INSERT INTO `Plan` VALUES ('1','Big Man on the Campus','Build Muscle','Male ','Beginner','Nam Vu','11/06/2014','12','5.5','48.5','98','200');";
	
	public CreateDatabase(Context context) {
		super(context,DATABASE_NAME,null,VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PLAN_TABLE);
		db.execSQL(INSERT_INTO_PLAN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	
}
