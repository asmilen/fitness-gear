package com.fitnessgear;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessgear.adapter.GridAdapter;
import com.fitnessgear.child.StartWorkOutDetail;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.WorkoutItem;

public class StartWorkOut extends Fragment {

	private GridView grid;
//	private ListView listWorkout;

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
	
	private int planID = 1;

//	private SQLiteDatabase db = null;

	public StartWorkOut() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_start_workout,
				container, false);

		

		grid = (GridView) rootView.findViewById(R.id.listExerciseOnPlan);

		planName = (TextView) rootView.findViewById(R.id.planName);
		author = (TextView) rootView.findViewById(R.id.author);
		txtGender = (TextView) rootView.findViewById(R.id.txtGender);
		txtMainGoal = (TextView) rootView.findViewById(R.id.txtMainGoal);
		txtLevelTrain = (TextView) rootView.findViewById(R.id.txtLevelTrain);
		txtDateCreated = (TextView) rootView.findViewById(R.id.txtDateCreated);

		txtTotalWeeks = (TextView) rootView.findViewById(R.id.txtTotalWeeks);
		txtAverageDay = (TextView) rootView.findViewById(R.id.txtAverageDay);
		txtArverageTime = (TextView) rootView
				.findViewById(R.id.txtArverageTime);
		txtTotalTime = (TextView) rootView.findViewById(R.id.txtTotalTime);
		txtTotalCadio = (TextView) rootView.findViewById(R.id.txtTotalCardio);

		// Call method
		getData();
		

		return rootView;
	}

	public void getData() {

		try {
			MainActivity.db = MainActivity.dbHelper.getReadableDatabase();
			Cursor c = MainActivity.db.rawQuery(
					"Select * " +
					"FROM Plan,Gender, FitnessLevel,Main_Goal " +
					"WHERE MainGoal = MainGoalID " +
					"AND Gender = GenderID " +
					"AND FitnessLevel = FitnessLevelID " +
					"AND PlanID = 1", null);

			ArrayList<String> data = new ArrayList<String>();
			while (c.moveToNext()) {
				// Lam bien dong
				// String s = "[" + c.getInt(c.getColumnIndex("PlanID")) + "] "
				// +
				// "" + c.getString(c.getColumnIndex("PlanName")) + " : " +
				// c.getInt(c.getColumnIndex("MainGoal"));
				// data.add(s);

				// planName.setText(DatabaseUltility.GetColumnValue(c,
				// DatabaseUltility.PlanName)+"");
				planName.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.PlanName));
				author.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.CreatedBy));
				txtGender.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.GenderName));
				txtMainGoal.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.MainGoalName));
				txtLevelTrain.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.FitnessLevelName));
				txtDateCreated.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.DateCreated));

				txtTotalWeeks.setText(""+DatabaseUltility.GetIntColumnValue(c, DatabaseUltility.TotalWeeks));
				txtAverageDay.setText(""+DatabaseUltility.GetFloatColumnValue(c, DatabaseUltility.AveDay));
				txtArverageTime.setText(""+DatabaseUltility.GetFloatColumnValue(c, DatabaseUltility.AveWorkoutTime)+"");
				txtTotalTime.setText(""+DatabaseUltility.GetIntColumnValue(c, DatabaseUltility.TotalTimeAWeek));
				txtTotalCadio.setText(""+DatabaseUltility.GetIntColumnValue(c, DatabaseUltility.TotalCardioTime));
			}
			Cursor workout = MainActivity.db.rawQuery("SELECT * FROM Workout WHERE PlanID = 1",
					null);
			//Khoi tao ArrayList cac WorkoutItem
			item = new ArrayList<WorkoutItem>();
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


		GridAdapter adapter = new GridAdapter(getActivity(), item);

		grid.setAdapter(adapter);
		//Set click for Grid View Start Workout
		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int pos = position;
				Intent intent = new Intent(getActivity(),
						StartWorkOutDetail.class);
				// item.get(position).getWorkoutID();
				intent.putExtra("WorkoutID", item.get(position).getWorkoutID());
				intent.putExtra("Day", (position + 1) + "");
				intent.putExtra("CreatedBy", author.getText().toString());
				intent.putExtra("ProgramFor", txtGender.getText().toString());
				intent.putExtra("MainGoal", txtMainGoal.getText().toString());
				intent.putExtra("Level", txtLevelTrain.getText().toString());
				intent.putExtra("PlanID", planID);
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
				LayoutInflater inflater = getActivity().getLayoutInflater();
				//Get View from inflater
				View updateWorkoutView = inflater.inflate(R.layout.activity_dialog_update_workout, null);
				
				txtWorkoutTime = (EditText) updateWorkoutView.findViewById(R.id.txtWorkoutTime);
				txtTotalCardioTime = (EditText) updateWorkoutView.findViewById(R.id.txtTotalCardioTime);
//				txtTotalExercise = (EditText) updateWorkoutView.findViewById(R.id.txtTotalExercise);
//				txtTotalSets = (EditText) updateWorkoutView.findViewById(R.id.txtTotalSets);
				txtDescription = (EditText) updateWorkoutView.findViewById(R.id.txtDescription);
				Cursor updateWorkoutCursor = MainActivity.db.rawQuery("Select * From Workout Where WorkoutID = " + workoutID, null);
				while(updateWorkoutCursor.moveToNext()){
					txtWorkoutTime.setText(""+DatabaseUltility.GetIntColumnValue(updateWorkoutCursor, DatabaseUltility.TotalWorkoutTime));
					txtTotalCardioTime.setText(""+DatabaseUltility.GetIntColumnValue(updateWorkoutCursor, DatabaseUltility.TotalCardioTime));
//					txtTotalExercise.setText(""+DatabaseUltility.GetIntColumnValue(updateWorkoutCursor, DatabaseUltility.TotalExercises));
//					txtTotalSets.setText(""+DatabaseUltility.GetIntColumnValue(updateWorkoutCursor, DatabaseUltility.TotalSets));
					txtDescription.setText(""+DatabaseUltility.GetColumnValue(updateWorkoutCursor, DatabaseUltility.Description));
				}
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				
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
//										int totalExercise = Integer.parseInt(txtTotalExercise.getText().toString());
//										int totalSets = Integer.parseInt(txtTotalSets.getText().toString());
										String description = txtDescription.getText().toString();
										MainActivity.db = MainActivity.dbHelper.getWritableDatabase();
										if(workoutTime > 5 && workoutTime <= 180 
												&& totalCardioTime >=0 && totalCardioTime <= 300
												&& !description.equals("")){
											ContentValues contentWorkout = new ContentValues();
											contentWorkout.put("TotalWorkoutTime", workoutTime);
											contentWorkout.put("TotalCardioTime", totalCardioTime);
	//										contentWorkout.put("TotalExercises", totalExercise);
	//										contentWorkout.put("TotalSets", totalSets);
											contentWorkout.put("Description",description);
											MainActivity.db.update("Workout", contentWorkout, "WorkoutID = ?", new String[] {workoutID});
											getData();
										}
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
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		} catch (Exception ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
