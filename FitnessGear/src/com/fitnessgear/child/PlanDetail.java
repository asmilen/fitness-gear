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
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class PlanDetail extends Activity {
	
	private GridView grid;
	private int WorkoutID;
	private int planID;
	private ArrayList<PlanItem> myListPlan;
	private GridAdapter adapter;

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
	
	private EditText txtWorkoutTime;
	private EditText txtTotalCardioTime;
	private EditText txtTotalExercise;
	private EditText txtTotalSets;
	private EditText txtDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_start_workout);
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		

		Cursor workoutIDCursor = MainActivity.db.rawQuery(
				"Select * from workout", null);

		while (workoutIDCursor.moveToNext()) {
			WorkoutID = DatabaseUltility.GetIntColumnValue(workoutIDCursor,
					DatabaseUltility.WorkoutID);
		}
		WorkoutID++;

		planID = getIntent().getIntExtra("PlanID", 0);

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
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Toast.makeText(getApplicationContext(), "Resume", Toast.LENGTH_LONG).show();
		
	}

	public void getData() {
		try {
			MainActivity.db = MainActivity.dbHelper.getReadableDatabase();
			Cursor planInformationCursor = MainActivity.db.rawQuery("Select * "
					+ "FROM Plan,Gender, FitnessLevel,Main_Goal "
					+ "WHERE MainGoal = MainGoalID " + "AND Gender = GenderID "
					+ "AND FitnessLevel = FitnessLevelID " + "AND PlanID = "
					+ planID, null);

			while (planInformationCursor.moveToNext()) {
				planName.setText(DatabaseUltility.GetColumnValue(
						planInformationCursor, DatabaseUltility.PlanName));
				author.setText(DatabaseUltility.GetColumnValue(
						planInformationCursor, DatabaseUltility.CreatedBy));
				txtGender.setText(DatabaseUltility.GetColumnValue(
						planInformationCursor, DatabaseUltility.GenderName));
				txtMainGoal.setText(DatabaseUltility.GetColumnValue(
						planInformationCursor, DatabaseUltility.MainGoalName));
				txtLevelTrain.setText(DatabaseUltility.GetColumnValue(
						planInformationCursor,
						DatabaseUltility.FitnessLevelName));
				txtDateCreated.setText(DatabaseUltility.GetColumnValue(
						planInformationCursor, DatabaseUltility.DateCreated));
				txtTotalWeeks.setText(""
						+ DatabaseUltility.GetIntColumnValue(
								planInformationCursor,
								DatabaseUltility.TotalWeeks));
				txtAverageDay
						.setText(""
								+ DatabaseUltility.GetFloatColumnValue(
										planInformationCursor,
										DatabaseUltility.AveDay));
				txtArverageTime.setText(""
						+ DatabaseUltility.GetFloatColumnValue(
								planInformationCursor,
								DatabaseUltility.AveWorkoutTime) + "");
				txtTotalTime.setText(""
						+ DatabaseUltility.GetIntColumnValue(
								planInformationCursor,
								DatabaseUltility.TotalTimeAWeek));
				txtTotalCadio.setText(""
						+ DatabaseUltility.GetIntColumnValue(
								planInformationCursor,
								DatabaseUltility.TotalCardioTime));

				// myListPlan.add(new PlanItem(
				// DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.PlanID),
				// DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.PlanName),
				// DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.MainGoalID),
				// DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.MainGoalName),
				// DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.GenderID),
				// DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.GenderName),
				// DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.FitnessLevelID),
				// DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.FitnessLevelName),
				// DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.CreatedBy),
				// DatabaseUltility.GetColumnValue(planInformationCursor,DatabaseUltility.DateCreated),
				// DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.TotalWeeks),
				// DatabaseUltility.GetFloatColumnValue(planInformationCursor,DatabaseUltility.AveDay),
				// DatabaseUltility.GetFloatColumnValue(planInformationCursor,DatabaseUltility.AveWorkoutTime),
				// DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.TotalCardioTime),
				// DatabaseUltility.GetIntColumnValue(planInformationCursor,DatabaseUltility.TotalTimeAWeek)));
			}
			Cursor workout = MainActivity.db.rawQuery(
					"SELECT * FROM Workout WHERE PlanID = " + planID, null);
			// Create ArrayList WorkoutItem
			item = new ArrayList<WorkoutItem>();
			// Add value to ArrayList
			while (workout.moveToNext()) {
				item.add(new WorkoutItem(DatabaseUltility.GetColumnValue(workout,
						DatabaseUltility.WorkoutID), DatabaseUltility
						.GetColumnValue(workout, DatabaseUltility.WorkoutName),
						DatabaseUltility.GetColumnValue(workout,
								DatabaseUltility.Description), DatabaseUltility
								.GetColumnValue(workout,
										DatabaseUltility.TotalWorkoutTime),
						DatabaseUltility.GetColumnValue(workout,
								DatabaseUltility.TotalCardioTime), DatabaseUltility
								.GetColumnValue(workout,
										DatabaseUltility.TotalExercises),
						DatabaseUltility.GetColumnValue(workout,
								DatabaseUltility.TotalSets)));
			}

			adapter = new GridAdapter(getApplicationContext(), item);

			grid.setAdapter(adapter);
			// Set click for Grid View Start Workout
			grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					int pos = position;
					Intent intent = new Intent(getApplicationContext(),
							StartWorkOutDetail.class);
					// item.get(position).getWorkoutID();
					intent.putExtra("WorkoutID", item.get(position).getWorkoutID());
					intent.putExtra("TotalWorkoutTime", item.get(position)
							.getTotalWorkoutTime());
					intent.putExtra("TotalCardioTime", item.get(position)
							.getTotalCardioTime());
					intent.putExtra("TotalExercises", item.get(position)
							.getTotalExercises());
					intent.putExtra("TotalSets", item.get(position).getTotalSets());
					intent.putExtra("Day", (position + 1) + "");
					intent.putExtra("CreatedBy", author.getText().toString());
					intent.putExtra("ProgramFor", txtGender.getText().toString());
					intent.putExtra("MainGoal", txtMainGoal.getText().toString());
					intent.putExtra("Level", txtLevelTrain.getText().toString());
					startActivity(intent);
				}
			});
			grid.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					final String workoutID = item.get(position).getWorkoutID();
					// Get the layout inflater
					LayoutInflater inflater = PlanDetail.this.getLayoutInflater();
					//Get View from inflater
					View updateWorkoutView = inflater.inflate(R.layout.activity_dialog_update_workout, null);
					
					txtWorkoutTime = (EditText) updateWorkoutView.findViewById(R.id.txtWorkoutTime);
					txtTotalCardioTime = (EditText) updateWorkoutView.findViewById(R.id.txtTotalCardioTime);
					txtTotalExercise = (EditText) updateWorkoutView.findViewById(R.id.txtTotalExercise);
					txtTotalSets = (EditText) updateWorkoutView.findViewById(R.id.txtTotalSets);
					txtDescription = (EditText) updateWorkoutView.findViewById(R.id.txtDescription);
					Cursor updateWorkoutCursor = MainActivity.db.rawQuery("Select * From Workout Where PlanID = " + planID + " AND WorkoutID = " + workoutID, null);
					while(updateWorkoutCursor.moveToNext()){
						txtWorkoutTime.setText(""+DatabaseUltility.GetIntColumnValue(updateWorkoutCursor, DatabaseUltility.TotalWorkoutTime));
						txtTotalCardioTime.setText(""+DatabaseUltility.GetIntColumnValue(updateWorkoutCursor, DatabaseUltility.TotalCardioTime));
						txtTotalExercise.setText(""+DatabaseUltility.GetIntColumnValue(updateWorkoutCursor, DatabaseUltility.TotalExercises));
						txtTotalSets.setText(""+DatabaseUltility.GetIntColumnValue(updateWorkoutCursor, DatabaseUltility.TotalSets));
						txtDescription.setText(""+DatabaseUltility.GetColumnValue(updateWorkoutCursor, DatabaseUltility.Description));
					}
					AlertDialog.Builder builder = new AlertDialog.Builder(
							PlanDetail.this);
					
					// Inflate and set the layout for the dialog
					// Pass null as the parent view because its going in the dialog
					// layout
					builder.setView(updateWorkoutView)
									.setTitle("Update Workout")
							// Add action buttons
							.setPositiveButton("Update",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog,
												int id) {
											int workoutTime = Integer.parseInt(txtWorkoutTime.getText().toString());
											int totalCardioTime = Integer.parseInt(txtTotalCardioTime.getText().toString());
											int totalExercise = Integer.parseInt(txtTotalExercise.getText().toString());
											int totalSets = Integer.parseInt(txtTotalSets.getText().toString());
											String description = txtDescription.getText().toString();
											MainActivity.db = MainActivity.dbHelper.getWritableDatabase();
											ContentValues contentWorkout = new ContentValues();
											contentWorkout.put("TotalWorkoutTime", workoutTime);
											contentWorkout.put("TotalCardioTime", totalCardioTime);
											contentWorkout.put("TotalExercises", totalExercise);
											contentWorkout.put("TotalSets", totalSets);
											contentWorkout.put("Description",description);
											MainActivity.db.update("Workout", contentWorkout, "WorkoutID = ?", new String[] {workoutID});
											getData();
										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int id) {
										}
									});
					builder.show();
					return true;
				}
			});
		} catch (SQLiteException ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	public void addToWorkout() {

		Toast.makeText(getApplicationContext(), "Workout ID " + WorkoutID,
				Toast.LENGTH_LONG).show();

		MainActivity.db = MainActivity.dbHelper.getWritableDatabase();
		Cursor planInformationCursor = MainActivity.db.rawQuery("Select * "
				+ "FROM Plan,Gender, FitnessLevel,Main_Goal "
				+ "WHERE MainGoal = MainGoalID " + "AND Gender = GenderID "
				+ "AND FitnessLevel = FitnessLevelID " + "AND PlanID = "
				+ planID, null);
		ContentValues contentPlan = new ContentValues();
		while (planInformationCursor.moveToNext()) {
			contentPlan.put("PlanName", DatabaseUltility.GetColumnValue(
					planInformationCursor, DatabaseUltility.PlanName));
			contentPlan.put("MainGoal", DatabaseUltility.GetIntColumnValue(
					planInformationCursor, DatabaseUltility.MainGoalID));
			contentPlan.put("Gender", DatabaseUltility.GetIntColumnValue(
					planInformationCursor, DatabaseUltility.GenderID));
			contentPlan.put("FitnessLevel", DatabaseUltility.GetIntColumnValue(
					planInformationCursor, DatabaseUltility.FitnessLevelID));
			contentPlan.put("CreatedBy", DatabaseUltility.GetColumnValue(
					planInformationCursor, DatabaseUltility.CreatedBy));
			contentPlan.put("DateCreated", DatabaseUltility.GetColumnValue(
					planInformationCursor, DatabaseUltility.DateCreated));
			contentPlan.put("TotalWeeks", DatabaseUltility.GetIntColumnValue(
					planInformationCursor, DatabaseUltility.TotalWeeks));
			contentPlan.put("AveDay", DatabaseUltility.GetFloatColumnValue(
					planInformationCursor, DatabaseUltility.AveDay));
			contentPlan.put("AveWorkoutTime", DatabaseUltility
					.GetFloatColumnValue(planInformationCursor,
							DatabaseUltility.AveWorkoutTime));
			contentPlan.put("TotalTimeAWeek", DatabaseUltility
					.GetIntColumnValue(planInformationCursor,
							DatabaseUltility.TotalTimeAWeek));
			contentPlan.put("TotalCardioTime", DatabaseUltility
					.GetIntColumnValue(planInformationCursor,
							DatabaseUltility.TotalCardioTime));
		}

		MainActivity.db.execSQL("Delete From Workout WHERE PlanID = 1");
		Cursor workoutInformationCursor = MainActivity.db.rawQuery(
				"SELECT * FROM Workout WHERE PlanID = " + planID, null);

		ContentValues contentWorkout = new ContentValues();
		ContentValues contentWorkoutExercise = new ContentValues();
		int workoutUpdate = 0;
		int workoutExerciseUpdate = 0;
		while (workoutInformationCursor.moveToNext()) {
			contentWorkout.put("WorkoutID", WorkoutID);
			contentWorkout.put("PlanID", 1);
			contentWorkout.put("WorkoutName", DatabaseUltility.GetColumnValue(
					workoutInformationCursor, DatabaseUltility.WorkoutName));
			contentWorkout.put("TotalWorkoutTime", DatabaseUltility
					.GetIntColumnValue(workoutInformationCursor,
							DatabaseUltility.TotalWorkoutTime));
			contentWorkout.put("TotalCardioTime", DatabaseUltility
					.GetIntColumnValue(workoutInformationCursor,
							DatabaseUltility.TotalCardioTime));
			contentWorkout.put("TotalExercises", DatabaseUltility
					.GetIntColumnValue(workoutInformationCursor,
							DatabaseUltility.TotalExercises));
			contentWorkout.put("TotalSets", DatabaseUltility.GetIntColumnValue(
					workoutInformationCursor, DatabaseUltility.TotalSets));
			contentWorkout.put("AvaImage", DatabaseUltility.GetColumnValue(
					workoutInformationCursor, DatabaseUltility.AvaImage));
			contentWorkout.put("Description", DatabaseUltility.GetColumnValue(
					workoutInformationCursor, DatabaseUltility.Description));
			MainActivity.db.insert("Workout", null, contentWorkout);
			MainActivity.db
					.execSQL("Delete From Workout_Exercise WHERE WorkoutID = "
							+ WorkoutID);
			Cursor workoutExerciseInformationCursor = MainActivity.db.rawQuery(
					"Select * From Workout_Exercise Where WorkoutID = "
							+ DatabaseUltility.GetColumnValue(
									workoutInformationCursor,
									DatabaseUltility.WorkoutID), null);
			while (workoutExerciseInformationCursor.moveToNext()) {
				contentWorkoutExercise.put("WorkoutID", WorkoutID);
				contentWorkoutExercise.put("ExerciseID", DatabaseUltility
						.GetIntColumnValue(workoutExerciseInformationCursor,
								DatabaseUltility.ExerciseID));
				contentWorkoutExercise.put("Sets", DatabaseUltility
						.GetIntColumnValue(workoutExerciseInformationCursor,
								DatabaseUltility.Sets));
				contentWorkoutExercise.put("RepsMin", DatabaseUltility
						.GetIntColumnValue(workoutExerciseInformationCursor,
								DatabaseUltility.RepsMin));
				contentWorkoutExercise.put("RepsMax", DatabaseUltility
						.GetIntColumnValue(workoutExerciseInformationCursor,
								DatabaseUltility.RepsMax));
				contentWorkoutExercise.put("Kg", DatabaseUltility
						.GetIntColumnValue(workoutExerciseInformationCursor,
								DatabaseUltility.Kg));
				contentWorkoutExercise.put("Rests", DatabaseUltility
						.GetIntColumnValue(workoutExerciseInformationCursor,
								DatabaseUltility.Rests));
				contentWorkoutExercise.put("Interval", DatabaseUltility
						.GetIntColumnValue(workoutExerciseInformationCursor,
								DatabaseUltility.Interval));
				MainActivity.db.insert("Workout_Exercise", null,
						contentWorkoutExercise);
			}
			WorkoutID++;

		}
		MainActivity.db.update("Plan", contentPlan, "PlanID = 1", null);
		// Toast.makeText(getApplicationContext(), workoutExerciseUpdate+"",
		// Toast.LENGTH_SHORT).show();
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
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
