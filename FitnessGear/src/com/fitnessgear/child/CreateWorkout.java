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

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class CreateWorkout extends Activity {

	View updateWorkoutView;
	
	private GridView grid;
	private int WorkoutID;
	private int numOfWorkout;
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
	
	private EditText txtWorkoutTime;
	private EditText txtTotalCardioTime;
//	private EditText txtTotalExercise;
//	private EditText txtTotalSets;
	private EditText txtDescription;

	private ArrayList<WorkoutItem> item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_start_workout);
		
		Toast.makeText(CreateWorkout.this, "Click Long Workout Item To Update Information", Toast.LENGTH_LONG).show();
		
		// Get the layout inflater
		LayoutInflater inflater = CreateWorkout.this.getLayoutInflater();
		//Get View from inflater
		updateWorkoutView = inflater.inflate(R.layout.activity_dialog_update_workout, null);
		
		txtWorkoutTime = (EditText) updateWorkoutView.findViewById(R.id.txtWorkoutTime);
		txtTotalCardioTime = (EditText) updateWorkoutView.findViewById(R.id.txtTotalCardioTime);
//		txtTotalExercise = (EditText) updateWorkoutView.findViewById(R.id.txtTotalExercise);
//		txtTotalSets = (EditText) updateWorkoutView.findViewById(R.id.txtTotalSets);
		txtDescription = (EditText) updateWorkoutView.findViewById(R.id.txtDescription);
		//Get PlanID and number of workout to create from previous activity
		planID = getIntent().getIntExtra("PlanID", 0);
		numOfWorkout = getIntent().getIntExtra("NumOfWorkout", 0);
		//Get last WorkoutID from workout table
		Cursor workoutIDCursor = MainActivity.db.rawQuery(
				"Select * from workout", null);

		while (workoutIDCursor.moveToNext()) {
			WorkoutID = DatabaseUltility.GetIntColumnValue(workoutIDCursor,
					DatabaseUltility.WorkoutID);
		}
		WorkoutID++;
		
		for(int i = 1; i <= numOfWorkout;i++){
			MainActivity.db.execSQL("Insert Into Workout (WorkoutID,PlanID,WorkoutName,TotalWorkoutTime,TotalCardioTime,TotalExercises,TotalSets,Description) Values (" + WorkoutID + "," + planID + ",'Day " + i + " Of Plan'"  + ",0,0,0,0,'Rest')");
			WorkoutID++;
		}		
		
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

		GridAdapter adapter = new GridAdapter(getApplicationContext(), item);

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
				LayoutInflater inflater = CreateWorkout.this.getLayoutInflater();
				// Get View from inflater
				View updateWorkoutView = inflater.inflate(
						R.layout.activity_dialog_update_workout, null);

				txtWorkoutTime = (EditText) updateWorkoutView
						.findViewById(R.id.txtWorkoutTime);
				txtTotalCardioTime = (EditText) updateWorkoutView
						.findViewById(R.id.txtTotalCardioTime);
//				txtTotalExercise = (EditText) updateWorkoutView
//						.findViewById(R.id.txtTotalExercise);
//				txtTotalSets = (EditText) updateWorkoutView
//						.findViewById(R.id.txtTotalSets);
				txtDescription = (EditText) updateWorkoutView
						.findViewById(R.id.txtDescription);
				Cursor updateWorkoutCursor = MainActivity.db.rawQuery(
						"Select * From Workout Where PlanID = " + planID
								+ " AND WorkoutID = " + workoutID, null);
				while (updateWorkoutCursor.moveToNext()) {
					txtWorkoutTime.setText(""
							+ DatabaseUltility.GetIntColumnValue(
									updateWorkoutCursor,
									DatabaseUltility.TotalWorkoutTime));
					txtTotalCardioTime.setText(""
							+ DatabaseUltility.GetIntColumnValue(
									updateWorkoutCursor,
									DatabaseUltility.TotalCardioTime));
//					txtTotalExercise.setText(""
//							+ DatabaseUltility.GetIntColumnValue(
//									updateWorkoutCursor,
//									DatabaseUltility.TotalExercises));
//					txtTotalSets.setText(""
//							+ DatabaseUltility.GetIntColumnValue(
//									updateWorkoutCursor,
//									DatabaseUltility.TotalSets));
					txtDescription.setText(""
							+ DatabaseUltility.GetColumnValue(
									updateWorkoutCursor,
									DatabaseUltility.Description));
				}
				AlertDialog.Builder builder = new AlertDialog.Builder(
						CreateWorkout.this);

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
										int workoutTime = Integer
												.parseInt(txtWorkoutTime
														.getText().toString());
										int totalCardioTime = Integer
												.parseInt(txtTotalCardioTime
														.getText().toString());
//										int totalExercise = Integer
//												.parseInt(txtTotalExercise
//														.getText().toString());
//										int totalSets = Integer
//												.parseInt(txtTotalSets
//														.getText().toString());
										String description = txtDescription
												.getText().toString();
										MainActivity.db = MainActivity.dbHelper
												.getWritableDatabase();
										ContentValues contentWorkout = new ContentValues();
										contentWorkout.put("TotalWorkoutTime",
												workoutTime);
										contentWorkout.put("TotalCardioTime",
												totalCardioTime);
//										contentWorkout.put("TotalExercises",
//												totalExercise);
//										contentWorkout.put("TotalSets",
//												totalSets);
										contentWorkout.put("Description",
												description);
										MainActivity.db.update("Workout",
												contentWorkout,
												"WorkoutID = ?",
												new String[] { workoutID });
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
	}
	
	public void getData() {
		try {
			MainActivity.db = MainActivity.dbHelper.getReadableDatabase();
			Cursor planInformationCursor = MainActivity.db.rawQuery("Select * "
					+ "FROM Plan,Gender, FitnessLevel,Main_Goal "
					+ "WHERE MainGoal = MainGoalID " + "AND Gender = GenderID "
					+ "AND FitnessLevel = FitnessLevelID " + "AND PlanID = "
					+ planID, null);

			ArrayList<String> data = new ArrayList<String>();
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
			}
		} catch (SQLiteException ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_workout, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
