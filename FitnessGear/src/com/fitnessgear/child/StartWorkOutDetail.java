package com.fitnessgear.child;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.R.id;
import com.fitnessgear.R.layout;
import com.fitnessgear.R.menu;
import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.model.WorkoutItem;
import com.fitnessgear.sapservices.HelloAccessoryProviderService;

public class StartWorkOutDetail extends Activity {

	private ArrayList<ExercisesItem> myListExercise;
	private ListExercisesAdapter adapter;
	private String message;
	String workoutID;

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
	
	private int planID;
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
		try {
			// Get Data from
			Bundle bund = getIntent().getExtras();

			// String workoutId = bund.getString("WorkoutID");

			String day = bund.getString("Day");
			String week = (((Integer.parseInt(day) - 1) / 7) + 1) + "";
			String createdBy = bund.getString("CreatedBy");
			String programFor = bund.getString("ProgramFor");
			String mainGoal = bund.getString("MainGoal");
			String level = bund.getString("Level");

			String totalExercises = bund.getString("TotalExercises");
			String totalSets = bund.getString("TotalSets");
			String totalWorkoutTime = bund.getString("TotalWorkoutTime");
			String totalCardio = bund.getString("TotalCardioTime");

			// Set text
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

			// Load list exercise tu database
			try {
				Bundle extras = getIntent().getExtras();
				workoutID = extras.getString("WorkoutID");

				planID = getIntent().getIntExtra("PlanID", 0);
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
				message = "";
				Cursor listExerciseCursor = MainActivity.db
						.rawQuery(
								"Select * "
										+ "FROM Workout_Exercise,Exercise "
										+ "Where Workout_exercise.ExerciseID = Exercise.ExerciseID "
										+ "AND WorkoutID = " + workoutID, null);
				int i = 0;
				while (listExerciseCursor.moveToNext()) {
					myListExercise
							.add(new ExercisesItem(
									DatabaseUltility
											.GetIntColumnValue(
													listExerciseCursor,
													"Workout_Exercise."
															+ DatabaseUltility.ExerciseID),
									DatabaseUltility.GetColumnValue(
											listExerciseCursor,
											DatabaseUltility.ExerciseName),
									DatabaseUltility.GetIntColumnValue(
											listExerciseCursor,
											DatabaseUltility.ExerciseType),
									DatabaseUltility.GetIntColumnValue(
											listExerciseCursor,
											DatabaseUltility.MuscleTarget),
									DatabaseUltility.GetIntColumnValue(
											listExerciseCursor,
											DatabaseUltility.Equipment),
									DatabaseUltility.GetFloatColumnValue(
											listExerciseCursor,
											DatabaseUltility.Rating),
									DatabaseUltility.GetColumnValue(
											listExerciseCursor,
											DatabaseUltility.Image1),
									DatabaseUltility.GetColumnValue(
											listExerciseCursor,
											DatabaseUltility.Image2),
									DatabaseUltility.GetColumnValue(
											listExerciseCursor,
											DatabaseUltility.Description),
									DatabaseUltility.GetIntColumnValue(
											listExerciseCursor,
											DatabaseUltility.Sets),
									DatabaseUltility.GetIntColumnValue(
											listExerciseCursor,
											DatabaseUltility.RepsMin),
									DatabaseUltility.GetIntColumnValue(
											listExerciseCursor,
											DatabaseUltility.RepsMax),
									DatabaseUltility.GetIntColumnValue(
											listExerciseCursor,
											DatabaseUltility.Kg),
									DatabaseUltility.GetIntColumnValue(
											listExerciseCursor,
											DatabaseUltility.Rests),
									DatabaseUltility.GetIntColumnValue(
											listExerciseCursor,
											DatabaseUltility.Interval)));

					int aveReps = (Integer.valueOf(myListExercise.get(i)
							.getRepsmin()) + Integer.valueOf(myListExercise
							.get(i).getRepsmax())) / 2;

					message += myListExercise.get(i).getExerciseID() + "."
							+ myListExercise.get(i).getImg1() + "."
							+ myListExercise.get(i).getImg2() + "."
							+ myListExercise.get(i).getSets() + "."
							+ String.valueOf(aveReps) + "."
							+ myListExercise.get(i).getKg() + "."
							+ myListExercise.get(i).getRests() + "."
							+ myListExercise.get(i).getInterval() + ";";

					// myListExercise.add(ExerciseID);
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
						exerciseDetailIntent.putExtra("ExerciseID",
								myListExercise.get(position).getExerciseID());
						// exerciseDetailIntent.putExtra("workoutID",
						// workoutID);
						startActivity(exerciseDetailIntent);
					}
				});
				listExercise
						.setOnItemLongClickListener(new OnItemLongClickListener() {

							@Override
							public boolean onItemLongClick(
									AdapterView<?> parent, View view,
									int position, long id) {
								// TODO Auto-generated method stub
								final int ExerciseID = myListExercise.get(
										position).getExerciseID();
								AlertDialog.Builder builder = new AlertDialog.Builder(
										StartWorkOutDetail.this);
								builder.setMessage("Are you want to delete")
										.setIcon(
												android.R.drawable.ic_dialog_alert)
										.setTitle("Warning")
										.setPositiveButton(
												"Delete",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated
														// method stub
														MainActivity.db = MainActivity.dbHelper
																.getWritableDatabase();
														int deleteExercise = MainActivity.db
																.delete("Workout_Exercise",
																		"ExerciseID = "
																				+ ExerciseID,
																		null);
														if (deleteExercise > 0) {
															AddExercise
																	.updateWorkoutAfterCalculate(workoutID);
															getData();
														}
													}
												})
										.setNegativeButton(
												"Cancel",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated
														// method stub

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
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	// Create Menu
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
		// Set action for Back button
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		// Set action for Track Workout
		if (id == R.id.track_workout) {
			new AlertDialog.Builder(this)
					.setTitle("Track workout")
					.setMessage(
							"Do you want to track workout on Phone or on Watch?")
					.setPositiveButton("On Phone",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// continue with delete
									if(myListExercise.size() <= 0){
										
									}
									else{
										Intent intent = new Intent(
												getApplicationContext(),
												TrackWorkout.class);
										intent.putExtra("listExercise",
												myListExercise);
										intent.putExtra("workoutID", workoutID);
										startActivity(intent);
									}
									
								}
							})
					.setNegativeButton("On Watch",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// do nothing
									HelloAccessoryProviderService
											.setMessage(message);
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();

			return true;

		}
		if (id == R.id.add_exercise) {
			Intent intent = new Intent(getApplicationContext(),AddExercise.class);
			intent.putExtra("WorkoutID", workoutID);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getData();
	}

}
