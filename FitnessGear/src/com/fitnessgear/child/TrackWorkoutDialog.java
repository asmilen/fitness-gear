package com.fitnessgear.child;

import java.util.ArrayList;

import com.fitnessgear.R;
import com.fitnessgear.R.id;
import com.fitnessgear.R.layout;
import com.fitnessgear.R.menu;
import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.model.ListExercisesItem;
import com.fitnessgear.model.LogExerciseItem;
import com.fitnessgear.sapservices.HelloAccessoryProviderService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class TrackWorkoutDialog extends Activity {

	private ArrayList<ListExercisesItem> myListExercise;
	private String message;
	String workoutID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track_workout_dialog);
		Bundle extras = getIntent().getExtras();
		myListExercise = (ArrayList<ListExercisesItem>) extras.getSerializable("listExercise");
		workoutID = extras.getString("workoutID");
		message = extras.getString("message");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.track_workout_dialog, menu);
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
	
	// Track workout on Phone
		public void OnPhone(View e) {
			try
			{
				Intent intent = new Intent(getApplicationContext(), TrackWorkout.class);
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
}
