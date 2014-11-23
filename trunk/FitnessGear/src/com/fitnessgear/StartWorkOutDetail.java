package com.fitnessgear;

import java.util.ArrayList;
import java.util.List;

import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.sapservices.HelloAccessoryProviderService;
import com.fitnessgear.sapservices.HelloAccessoryProviderService.HelloAccessoryProviderConnection;

import android.R.anim;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StartWorkOutDetail extends Activity {

	ArrayList<String> myListExercise;
	String message;
	String workoutID;
	
//	private SQLiteDatabase db;
	
	private TextView txtNumOfDay;
	private TextView txtNumOfWeek;
	private TextView txtCreatedBy;
	private TextView txtProgramFor;
	private TextView txtMainGoal;
	private TextView txtLevelTrain;
	private TextView txtTotalExercises;
	private TextView txtTotalSets;
	private TextView txtArverageTime;
	private TextView txtTotalCardio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load giao diện
		setContentView(R.layout.activity_start_work_out_detail);
		//Set Back button
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		txtNumOfDay = (TextView) findViewById(R.id.txtNumOfDay);
		txtNumOfWeek = (TextView) findViewById(R.id.txtNumOfWeek);
		txtCreatedBy = (TextView) findViewById(R.id.txtCreatedBy);
		txtProgramFor = (TextView) findViewById(R.id.txtProgramFor);
		txtMainGoal = (TextView) findViewById(R.id.txtMainGoal);
		txtLevelTrain = (TextView) findViewById(R.id.txtLevelTrain);
		
		txtTotalExercises = (TextView) findViewById(R.id.txtTotalExercises);
		txtTotalSets = (TextView) findViewById(R.id.txtTotalSets);
		txtArverageTime = (TextView) findViewById(R.id.txtArverageTime);
		txtTotalCardio = (TextView) findViewById(R.id.txtTotalCardio);
		
		Bundle bund = getIntent().getExtras();
		
//		String workoutId = bund.getString("WorkoutID");
		
		String day = bund.getString("Day");
		String week = (((Integer.parseInt(day)-1)/7)+1)+ "";
		String createdBy = bund.getString("CreatedBy");
		String programFor = bund.getString("ProgramFor");
		String mainGoal = bund.getString("MainGoal");
		String level = bund.getString("Level");
		
		String totalExercises = bund.getString("TotalExercises");
		String totalSets = bund.getString("TotalSets");
		String totalWorkoutTime = bund.getString("TotalWorkoutTime");
		String totalCardio = bund.getString("TotalCardioTime");
		
		//Set text
		txtNumOfDay.setText(day);
		txtNumOfWeek.setText(week);
		txtCreatedBy.setText(createdBy);
		txtProgramFor.setText(programFor);
		txtMainGoal.setText(mainGoal);
		txtLevelTrain.setText(level);
		
		txtTotalExercises.setText(totalExercises);
		txtTotalSets.setText(totalSets);
		txtArverageTime.setText(totalWorkoutTime);
		txtTotalCardio.setText(totalCardio);
		
//		Cursor listExercises = db.rawQuery(
//				"Select * FROM Workout_Exercise Where WorkoutID = " + workoutId, null);
		
		// Load list exercise tu database
		try {
			Bundle extras = getIntent().getExtras();
			workoutID = extras.getString("WorkoutID");
			
//			MainActivity.dbHelper = new DataBaseHelper(getApplicationContext());
			MainActivity.db = MainActivity.dbHelper.getReadableDatabase();

			myListExercise = new ArrayList<String>();
			message = "";

			Cursor c = MainActivity.db.rawQuery(
					"Select * FROM Workout_Exercise Where WorkoutID = " + workoutID, null);
			int i = 0;
			Toast.makeText(getApplicationContext(), workoutID,
					Toast.LENGTH_LONG).show();
			while (c.moveToNext()) {
				String ExerciseID = c.getString(c.getColumnIndex("ExerciseID"))+ "";
				String Sets = c.getString(c.getColumnIndex("Sets")) + "";
				String Reps = c.getString(c.getColumnIndex("Reps")) + "";
				String Rests = c.getString(c.getColumnIndex("Rests")) + "";
				String img1 = "", img2 = "";
				Cursor c1 = MainActivity.db.rawQuery("Select * FROM Exercise Where ExerciseID="+ ExerciseID, null);
				while (c1.moveToNext()) {
					// Set img base64
					img1 = c1.getString(c1.getColumnIndex("Image1"));
					img2 = c1.getString(c1.getColumnIndex("Image2"));
				}

				message += ExerciseID + "." + img1 + "." + img2 + "." + Sets
						+ "." + Reps + "." + Rests + ";";
				myListExercise.add(ExerciseID);
				i++;
			}
		} catch (SQLiteException ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		catch (Exception ex)
		{
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	// Track workout on Phone
	public void OnPhone() {
		try
		{
			Intent intent = new Intent(StartWorkOutDetail.this, TrackWorkout.class);
			intent.putExtra("listExercise", myListExercise);
			intent.putExtra("workoutID", workoutID);
			startActivity(intent);
		}
		catch (Exception ex)
		{
			Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	// Track workout on Watch
	public void OnWatch(View e) {
		HelloAccessoryProviderService.setMessage(message);
	}

	//Create Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_work_out_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		//Set action for Back button
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		//Set action for Track Workout
		if(id==R.id.track_workout){
			OnPhone();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
