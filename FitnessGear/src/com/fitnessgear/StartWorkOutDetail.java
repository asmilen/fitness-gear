package com.fitnessgear;

import java.util.ArrayList;
import java.util.List;

import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.sapservices.HelloAccessoryProviderService;
import com.fitnessgear.sapservices.HelloAccessoryProviderService.HelloAccessoryProviderConnection;

import android.R.anim;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StartWorkOutDetail extends Activity {

	private ArrayList<ExercisesItem> myListExercise;
	private ListExercisesAdapter adapter;
	private String message;
	String workoutID;
	
//	private SQLiteDatabase db;
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

			//Khoi tao ArrayList tu ExercisesItem class va String message
			myListExercise = new ArrayList<ExercisesItem>();
			message = "";
			Cursor listExerciseCursor = MainActivity.db.rawQuery(
					"Select * " +
					"FROM Workout_Exercise,Exercise " +
					"Where Workout_exercise.ExerciseID = Exercise.ExerciseID " +
					"AND WorkoutID = " + workoutID, null);
			int i = 0;
//			Toast.makeText(getApplicationContext(), workoutId,
//					Toast.LENGTH_LONG).show();
			while (listExerciseCursor.moveToNext()) {
				myListExercise.add(new ExercisesItem(
						DatabaseUltility.GetColumnValue(listExerciseCursor, "Workout_Exercise." + DatabaseUltility.ExerciseID),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.ExerciseName),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.ExerciseType),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.MuscleTarget),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.Equipment),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.Rating),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.Image1),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.Image2),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.Description),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.Sets),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.RepsMin),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.RepsMax),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.Kg),
						DatabaseUltility.GetColumnValue(listExerciseCursor, DatabaseUltility.Rests)));
//				String ExerciseID = c.getString(c.getColumnIndex("ExerciseID"))+ "";
//				String Sets = c.getString(c.getColumnIndex("Sets")) + "";
//				String Reps = c.getString(c.getColumnIndex("Reps")) + "";
//				String Rests = c.getString(c.getColumnIndex("Rests")) + "";
//				String img1 = "", img2 = "";
//				Cursor c1 = MainActivity.db.rawQuery("Select * FROM Exercise Where ExerciseID="+ ExerciseID, null);
//				while (c1.moveToNext()) {
//					// Set img base64
//					img1 = c1.getString(c1.getColumnIndex("Image1"));
//					img2 = c1.getString(c1.getColumnIndex("Image2"));
//				}
//=======
//			Toast.makeText(getApplicationContext(), workoutID,
//					Toast.LENGTH_LONG).show();
//			while (c.moveToNext()) {
//				String ExerciseID = c.getString(c.getColumnIndex("ExerciseID"))+ "";
//				String Sets = c.getString(c.getColumnIndex("Sets")) + "";
//				String Reps = c.getString(c.getColumnIndex("Reps")) + "";
//				String Rests = c.getString(c.getColumnIndex("Rests")) + "";
//				String img1 = "", img2 = "";
//				Cursor c1 = MainActivity.db.rawQuery("Select * FROM Exercise Where ExerciseID="+ ExerciseID, null);
//				while (c1.moveToNext()) {
//					// Set img base64
//					img1 = c1.getString(c1.getColumnIndex("Image1"));
//					img2 = c1.getString(c1.getColumnIndex("Image2"));
//				}
//>>>>>>> .r49

				int aveReps = (Integer.valueOf(myListExercise.get(i).getRepsmin())
						+	Integer.valueOf(myListExercise.get(i).getRepsmax()))/2;
				
				message += myListExercise.get(i).getExerciseID() 
						+ "." + myListExercise.get(i).getImg1() 
						+ "." + myListExercise.get(i).getImg2() 
						+ "." + myListExercise.get(i).getSets()
						+ "." + String.valueOf(aveReps)
						+ "." + myListExercise.get(i).getKg()
						+ "." + myListExercise.get(i).getRests() + ";";
				
//				myListExercise.add(ExerciseID);
				i++;
			}
			
			adapter = new ListExercisesAdapter(getApplicationContext(), myListExercise);
			
			listExercise.setAdapter(adapter);
			
			listExercise.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent exerciseDetailIntent = new Intent(getApplicationContext(),ExerciseDetail.class);
					exerciseDetailIntent.putExtra("ExerciseID", myListExercise.get(position).getExerciseID());
//					exerciseDetailIntent.putExtra("workoutID", workoutID);
					startActivity(exerciseDetailIntent);
				}
			});
			
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
			Intent track_intent = new Intent(getApplicationContext(),TrackWorkoutDialog.class);
			track_intent.putExtra("listExercise", myListExercise);
			track_intent.putExtra("workoutID", workoutID);
			track_intent.putExtra("message", message);
			startActivity(track_intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
//	//create Dialog@Override
//	public Dialog onCreateDialog(Bundle savedInstanceState) {
//	    mSelectedItems = new ArrayList();  // Where we track the selected items
//	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//	    // Set the dialog title
//	    builder.setTitle(R.string.pick_toppings)
//	    // Specify the list array, the items to be selected by default (null for none),
//	    // and the listener through which to receive callbacks when items are selected
//	           .setMultiChoiceItems(R.array.toppings, null,
//	                      new DialogInterface.OnMultiChoiceClickListener() {
//	               @Override
//	               public void onClick(DialogInterface dialog, int which,
//	                       boolean isChecked) {
//	                   if (isChecked) {
//	                       // If the user checked the item, add it to the selected items
//	                       mSelectedItems.add(which);
//	                   } else if (mSelectedItems.contains(which)) {
//	                       // Else, if the item is already in the array, remove it 
//	                       mSelectedItems.remove(Integer.valueOf(which));
//	                   }
//	               }
//	           })
//	    // Set the action buttons
//	           .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//	               @Override
//	               public void onClick(DialogInterface dialog, int id) {
//	                   // User clicked OK, so save the mSelectedItems results somewhere
//	                   // or return them to the component that opened the dialog
//	                   ...
//	               }
//	           })
//	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//	               @Override
//	               public void onClick(DialogInterface dialog, int id) {
//	                   ...
//	               }
//	           });
//
//	    return builder.create();
//	}
	
}
