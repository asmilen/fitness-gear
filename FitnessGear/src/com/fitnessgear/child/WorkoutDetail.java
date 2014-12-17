package com.fitnessgear.child;

import java.util.ArrayList;

import com.fitnessgear.ExerciseLibrary;
import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.R.id;
import com.fitnessgear.R.layout;
import com.fitnessgear.R.menu;
import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.adapter.ListFilterAdapter;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.EquipmentItem;
import com.fitnessgear.model.ExerciseTypeItem;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.model.FilterItem;
import com.fitnessgear.model.MuscleTargetItem;
import com.fitnessgear.model.WorkoutItem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class WorkoutDetail extends Activity {

	private ArrayList<ExercisesItem> myListExercise;
	private ListExercisesAdapter adapter;
//	private String message;
	private String workoutID;
	private int planID;

	// private SQLiteDatabase db;
	private ListView listExercise;

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
	
	private ArrayList<WorkoutItem> item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load giao diện
		setContentView(R.layout.activity_start_work_out_detail);
		// Set Back button
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		listExercise = (ListView) findViewById(R.id.listExercises);

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
		
		getData();

	}

	public void getData() {
		// Load list exercise tu database
		try {
			Bundle bund = getIntent().getExtras();
			workoutID = bund.getString("WorkoutID");
			
			String day = bund.getString("Day");
			String week = (((Integer.parseInt(day) - 1) / 7) + 1) + "";
			planID = getIntent().getIntExtra("PlanID", 0);
			Toast.makeText(getApplicationContext(), "PlanID: " + planID, Toast.LENGTH_LONG).show();
			
			String createdBy = bund.getString("CreatedBy");
			String programFor = bund.getString("ProgramFor");
			String mainGoal = bund.getString("MainGoal");
			String level = bund.getString("Level");

			// Set text
			txtNumOfDay.setText(day);
			txtNumOfWeek.setText(week);
			txtCreatedBy.setText(createdBy);
			txtProgramFor.setText(programFor);
			txtMainGoal.setText(mainGoal);
			txtLevelTrain.setText(level);
			
			Cursor workout = MainActivity.db.rawQuery(
					"SELECT * FROM Workout WHERE PlanID = " + planID + " AND WorkoutID = " + workoutID, null);
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
				

				txtTotalExercises.setText(DatabaseUltility
						.GetColumnValue(workout,
								DatabaseUltility.TotalExercises));
				txtTotalSets.setText(DatabaseUltility.GetColumnValue(workout,
						DatabaseUltility.TotalSets));
				txtArverageTime.setText(DatabaseUltility
						.GetColumnValue(workout,
								DatabaseUltility.TotalWorkoutTime));
				txtTotalCardio.setText(DatabaseUltility.GetColumnValue(workout,
						DatabaseUltility.TotalCardioTime));
			}
			// Khoi tao ArrayList tu ExercisesItem class va String message
			myListExercise = new ArrayList<ExercisesItem>();
			Cursor listExerciseCursor = MainActivity.db
					.rawQuery(
							"Select * "
									+ "FROM Workout_Exercise,Exercise "
									+ "Where Workout_exercise.ExerciseID = Exercise.ExerciseID "
									+ "AND WorkoutID = " + workoutID, null);
			int i = 0;
			while (listExerciseCursor.moveToNext()) {
				myListExercise.add(new ExercisesItem(DatabaseUltility
						.GetIntColumnValue(listExerciseCursor,
								"Workout_Exercise."
										+ DatabaseUltility.ExerciseID),
						DatabaseUltility.GetColumnValue(listExerciseCursor,
								DatabaseUltility.ExerciseName),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.ExerciseType),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.MuscleTarget),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.Equipment), DatabaseUltility
								.GetFloatColumnValue(listExerciseCursor,
										DatabaseUltility.Rating),
						DatabaseUltility.GetColumnValue(listExerciseCursor,
								DatabaseUltility.Image1), DatabaseUltility
								.GetColumnValue(listExerciseCursor,
										DatabaseUltility.Image2),
						DatabaseUltility.GetColumnValue(listExerciseCursor,
								DatabaseUltility.Description), DatabaseUltility
								.GetIntColumnValue(listExerciseCursor,
										DatabaseUltility.Sets),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.RepsMin), DatabaseUltility
								.GetIntColumnValue(listExerciseCursor,
										DatabaseUltility.RepsMax),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.Kg), DatabaseUltility
								.GetIntColumnValue(listExerciseCursor,
										DatabaseUltility.Rests),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.Interval)));
				i++;
			}

			adapter = new ListExercisesAdapter(getApplicationContext(),
					myListExercise);

			listExercise.setAdapter(adapter);

			listExercise.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent exerciseDetailIntent = new Intent(
							getApplicationContext(), ExerciseDetail.class);
					exerciseDetailIntent.putExtra("ExerciseID", myListExercise
							.get(position).getExerciseID());
					startActivity(exerciseDetailIntent);
				}
			});
			listExercise.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
					// TODO Auto-generated method stub
					final int ExerciseID = myListExercise.get(position).getExerciseID();
					AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutDetail.this);
					builder.setMessage("Are you want to delete")
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("Warning")
					.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							int deleteExercise = MainActivity.db.delete("Workout_Exercise", "ExerciseID = " + ExerciseID, null);
							if(deleteExercise > 0){
								Toast.makeText(WorkoutDetail.this, "Delete Exercise " + ExerciseID, Toast.LENGTH_LONG).show();
								AddExercise.updateWorkoutAfterCalculate(workoutID);
								getData();
							}
						}
					})
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					builder.show();
					
					return true;
				}
			});

		} catch (SQLiteException ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.workout_detail, menu);
		return true;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getData();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		if (id == R.id.add_exercise) {
			Intent intent = new Intent(getApplicationContext(),AddExercise.class);
			intent.putExtra("WorkoutID", workoutID);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
