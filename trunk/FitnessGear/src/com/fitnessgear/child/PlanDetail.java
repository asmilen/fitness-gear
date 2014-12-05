package com.fitnessgear.child;

import java.util.ArrayList;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.R.id;
import com.fitnessgear.R.layout;
import com.fitnessgear.R.menu;
import com.fitnessgear.adapter.GridAdapter;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.PlanItem;
import com.fitnessgear.model.WorkoutItem;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class PlanDetail extends Activity {

	private GridView grid;
//	private ListView listWorkout;
	private int planID;
	private ArrayList<PlanItem> myListPlan;
	
	private TextView planName;
	private TextView author;
	private TextView txtGender;
	private TextView txtMainGoal;
	private TextView txtLevelTrain;
	private TextView txtDateCreated;

	private TextView txtTotalWeeks;
	private TextView txtAverageDay;
	private TextView txtArverageTime;
	private TextView txtTotalTime;
	private TextView txtTotalCadio;

	private ArrayList<WorkoutItem> item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_start_workout);
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		
		planID = getIntent().getIntExtra("PlanID", 0);
		
		Cursor workout = MainActivity.db.rawQuery("SELECT * FROM Workout WHERE PlanID = " + planID,
				null);
		//Create ArrayList WorkoutItem
		item = new ArrayList<WorkoutItem>();

		grid = (GridView) findViewById(R.id.listExerciseOnPlan);

		planName = (TextView) findViewById(R.id.planName);
		author = (TextView) findViewById(R.id.author);
		txtGender = (TextView) findViewById(R.id.txtGender);
		txtMainGoal = (TextView) findViewById(R.id.txtMainGoal);
		txtLevelTrain = (TextView) findViewById(R.id.txtLevelTrain);
		txtDateCreated = (TextView) findViewById(R.id.txtDateCreated);

		txtTotalWeeks = (TextView) findViewById(R.id.txtTotalWeeks);
		txtAverageDay = (TextView) findViewById(R.id.txtAverageDay);
		txtArverageTime = (TextView) findViewById(R.id.txtArverageTime);
		txtTotalTime = (TextView) findViewById(R.id.txtTotalTime);
		txtTotalCadio = (TextView) findViewById(R.id.txtTotalCardio);

		// Call method
		getData();
		//Add value to ArrayList
			while (workout.moveToNext()) {
				item.add(new WorkoutItem(
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.WorkoutID),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.WorkoutName),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.Description),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.TotalWorkoutTime),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.TotalCardioTime),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.TotalExercises),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.TotalSets)));
			}

		GridAdapter adapter = new GridAdapter(getApplicationContext(), item);

		grid.setAdapter(adapter);
		//Set click for Grid View Start Workout
		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int pos = position;
				Intent intent = new Intent(getApplicationContext(),
						StartWorkOutDetail.class);
				// item.get(position).getWorkoutID();
				intent.putExtra("WorkoutID", item.get(position).getWorkoutID());
				intent.putExtra("TotalWorkoutTime", item.get(position).getTotalWorkoutTime());
				intent.putExtra("TotalCardioTime", item.get(position).getTotalCardioTime());
				intent.putExtra("TotalExercises", item.get(position).getTotalExercises());
				intent.putExtra("TotalSets", item.get(position).getTotalSets());
				intent.putExtra("Day", (position+1)+"");
				intent.putExtra("CreatedBy", author.getText().toString());
				intent.putExtra("ProgramFor", txtGender.getText().toString());
				intent.putExtra("MainGoal", txtMainGoal.getText().toString());
				intent.putExtra("Level", txtLevelTrain.getText().toString());
				startActivity(intent);
			}
		});
	}
	
	public void getData(){
		try {
			MainActivity.db = MainActivity.dbHelper.getReadableDatabase();
			Cursor planInformationCursor = MainActivity.db.rawQuery(
					"Select * " +
					"FROM Plan,Gender, FitnessLevel,Main_Goal " +
					"WHERE MainGoal = MainGoalID " +
					"AND Gender = GenderID " +
					"AND FitnessLevel = FitnessLevelID " +
					"AND PlanID = " + planID, null);

			ArrayList<String> data = new ArrayList<String>();
			while (planInformationCursor.moveToNext()) {
				planName.setText(DatabaseUltility.GetColumnValue(planInformationCursor, DatabaseUltility.PlanName));
				author.setText(DatabaseUltility.GetColumnValue(planInformationCursor, DatabaseUltility.CreatedBy));
				txtGender.setText(DatabaseUltility.GetColumnValue(planInformationCursor, DatabaseUltility.GenderName));
				txtMainGoal.setText(DatabaseUltility.GetColumnValue(planInformationCursor, DatabaseUltility.MainGoalName));
				txtLevelTrain.setText(DatabaseUltility.GetColumnValue(planInformationCursor, DatabaseUltility.FitnessLevelName));
				txtDateCreated.setText(DatabaseUltility.GetColumnValue(planInformationCursor, DatabaseUltility.DateCreated));
				txtTotalWeeks.setText(""+DatabaseUltility.GetIntColumnValue(planInformationCursor, DatabaseUltility.TotalWeeks));
				txtAverageDay.setText(""+DatabaseUltility.GetFloatColumnValue(planInformationCursor, DatabaseUltility.AveDay));
				txtArverageTime.setText(""+DatabaseUltility.GetFloatColumnValue(planInformationCursor, DatabaseUltility.AveWorkoutTime)+"");
				txtTotalTime.setText(""+DatabaseUltility.GetIntColumnValue(planInformationCursor, DatabaseUltility.TotalTimeAWeek));
				txtTotalCadio.setText(""+DatabaseUltility.GetIntColumnValue(planInformationCursor, DatabaseUltility.TotalCardioTime));
				
//				myListPlan.add(new PlanItem(
//						DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.PlanID),
//						DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.PlanName),
//						DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.MainGoalID),
//						DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.MainGoalName),
//						DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.GenderID),
//						DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.GenderName),
//						DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.FitnessLevelID),
//						DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.FitnessLevelName),
//						DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.CreatedBy),
//						DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.DateCreated),
//						DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.TotalWeeks),
//						DatabaseUltility.GetFloatColumnValue(planInformationCursor,DatabaseUltility.AveDay),
//						DatabaseUltility.GetFloatColumnValue(planInformationCursor,DatabaseUltility.AveWorkoutTime),
//						DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.TotalCardioTime),
//						DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.TotalTimeAWeek)));
			}
		} catch (SQLiteException ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}
	
	public void addToWorkout(){
		int WorkoutID = 1000;
		MainActivity.db = MainActivity.dbHelper.getWritableDatabase();
		Cursor planInformationCursor = MainActivity.db.rawQuery(
				"Select * " +
				"FROM Plan,Gender, FitnessLevel,Main_Goal " +
				"WHERE MainGoal = MainGoalID " +
				"AND Gender = GenderID " +
				"AND FitnessLevel = FitnessLevelID " +
				"AND PlanID = " + planID, null);
		ContentValues contentPlan = new ContentValues();
		while(planInformationCursor.moveToNext()){
			contentPlan.put("PlanName", DatabaseUltility.GetColumnValue(planInformationCursor, DatabaseUltility.PlanName));
			contentPlan.put("MainGoal", DatabaseUltility.GetIntColumnValue(planInformationCursor, DatabaseUltility.MainGoalID));
			contentPlan.put("Gender", DatabaseUltility.GetIntColumnValue(planInformationCursor, DatabaseUltility.GenderID));
			contentPlan.put("FitnessLevel", DatabaseUltility.GetIntColumnValue(planInformationCursor, DatabaseUltility.FitnessLevelID));
			contentPlan.put("CreatedBy", DatabaseUltility.GetColumnValue(planInformationCursor, DatabaseUltility.CreatedBy));
			contentPlan.put("DateCreated", DatabaseUltility.GetColumnValue(planInformationCursor, DatabaseUltility.DateCreated));
			contentPlan.put("TotalWeeks", DatabaseUltility.GetIntColumnValue(planInformationCursor, DatabaseUltility.TotalWeeks));
			contentPlan.put("AveDay", DatabaseUltility.GetFloatColumnValue(planInformationCursor, DatabaseUltility.AveDay));
			contentPlan.put("AveWorkoutTime", DatabaseUltility.GetFloatColumnValue(planInformationCursor, DatabaseUltility.AveWorkoutTime));
			contentPlan.put("TotalTimeAWeek", DatabaseUltility.GetIntColumnValue(planInformationCursor, DatabaseUltility.TotalTimeAWeek));
			contentPlan.put("TotalCardioTime", DatabaseUltility.GetIntColumnValue(planInformationCursor, DatabaseUltility.TotalCardioTime));
		}
		
		MainActivity.db.execSQL("Delete From Workout WHERE PlanID = 1");
		Cursor workoutInformationCursor = MainActivity.db.rawQuery("SELECT * FROM Workout WHERE PlanID = " + planID, null);
		
		ContentValues contentWorkout = new ContentValues();
		ContentValues contentWorkoutExercise = new ContentValues();
		int workoutUpdate = 0;
		int workoutExerciseUpdate = 0;
		while(workoutInformationCursor.moveToNext()){
			contentWorkout.put("WorkoutID", WorkoutID);
			contentWorkout.put("PlanID", 1);
			contentWorkout.put("WorkoutName", DatabaseUltility.GetColumnValue(workoutInformationCursor, DatabaseUltility.WorkoutName));
			contentWorkout.put("TotalWorkoutTime", DatabaseUltility.GetIntColumnValue(workoutInformationCursor, DatabaseUltility.TotalWorkoutTime));
			contentWorkout.put("TotalCardioTime", DatabaseUltility.GetIntColumnValue(workoutInformationCursor, DatabaseUltility.TotalCardioTime));
			contentWorkout.put("TotalExercises", DatabaseUltility.GetIntColumnValue(workoutInformationCursor, DatabaseUltility.TotalExercises));
			contentWorkout.put("TotalSets", DatabaseUltility.GetIntColumnValue(workoutInformationCursor, DatabaseUltility.TotalSets));
			contentWorkout.put("AvaImage", DatabaseUltility.GetColumnValue(workoutInformationCursor, DatabaseUltility.AvaImage));
			contentWorkout.put("Description", DatabaseUltility.GetColumnValue(workoutInformationCursor, DatabaseUltility.Description));
			workoutUpdate = (int) MainActivity.db.insert("Workout", null, contentWorkout);
			MainActivity.db.execSQL("Delete From Workout_Exercise WHERE WorkoutID = " + WorkoutID);
			Cursor workoutExerciseInformationCursor = MainActivity.db.rawQuery("Select * From Workout_Exercise Where WorkoutID = " + DatabaseUltility.GetColumnValue(workoutInformationCursor, DatabaseUltility.WorkoutID), null);
			while(workoutExerciseInformationCursor.moveToNext()){
				contentWorkoutExercise.put("WorkoutID", WorkoutID);
				contentWorkoutExercise.put("ExerciseID", DatabaseUltility.GetIntColumnValue(workoutExerciseInformationCursor, DatabaseUltility.ExerciseID));
				contentWorkoutExercise.put("Sets", DatabaseUltility.GetIntColumnValue(workoutExerciseInformationCursor, DatabaseUltility.Sets));
				contentWorkoutExercise.put("RepsMin", DatabaseUltility.GetIntColumnValue(workoutExerciseInformationCursor, DatabaseUltility.RepsMin));
				contentWorkoutExercise.put("RepsMax", DatabaseUltility.GetIntColumnValue(workoutExerciseInformationCursor, DatabaseUltility.RepsMax));
				contentWorkoutExercise.put("Kg", DatabaseUltility.GetIntColumnValue(workoutExerciseInformationCursor, DatabaseUltility.Kg));
				contentWorkoutExercise.put("Rests", DatabaseUltility.GetIntColumnValue(workoutExerciseInformationCursor, DatabaseUltility.Rests));
				contentWorkoutExercise.put("Interval", DatabaseUltility.GetIntColumnValue(workoutExerciseInformationCursor, DatabaseUltility.Interval));
				MainActivity.db.insert("Workout_Exercise", null, contentWorkoutExercise);
			}
			WorkoutID++;
		}
		MainActivity.db.update("Plan", contentPlan, "PlanID = 1", null);
//		Toast.makeText(getApplicationContext(), workoutExerciseUpdate+"", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plan_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.add_to_workout) {
			addToWorkout();
			return true;
		}
		if(id == android.R.id.home){
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
