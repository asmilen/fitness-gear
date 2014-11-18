package com.fitnessgear;

import java.util.ArrayList;
import java.util.List;

import com.fitnessgear.database.CreateDatabase;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class StartWorkOutDetail extends Activity {

	ArrayList<String> myListExercise;
	String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load giao diện
		setContentView(R.layout.activity_start_work_out_detail);
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		// Load list exercise tu database
		

		try {
			Bundle extras = getIntent().getExtras();
			String workoutId = extras.getInt("Day") + "";
			
			DataBaseHelper helper = new DataBaseHelper(getApplicationContext());
			SQLiteDatabase db = helper.getReadableDatabase();

			myListExercise = new ArrayList<String>();
			message = "";

			Cursor c = db.rawQuery(
					"Select * FROM Workout_Exercise Where WorkoutID=1", null);
			int i = 0;
			while (c.moveToNext()) {
				Toast.makeText(getApplicationContext(), i+"",
						Toast.LENGTH_LONG).show();
				String ExerciseID = c.getString(c.getColumnIndex("ExerciseID"))+ "";
				String Sets = c.getString(c.getColumnIndex("Sets")) + "";
				String Reps = c.getString(c.getColumnIndex("Reps")) + "";
				String Rests = c.getString(c.getColumnIndex("Rests")) + "";
				String img1 = "", img2 = "";
				Cursor c1 = db.rawQuery("Select * FROM Exercise Where ExerciseID="+ ExerciseID, null);
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
	public void OnPhone(View e) {
		Intent intent = new Intent(getApplicationContext(), TrackWorkout.class);
		intent.putStringArrayListExtra("listExercise", myListExercise);
		startActivity(intent);
	}

	// Track workout on Watch
	public void OnWatch(View e) {
		HelloAccessoryProviderService.setMessage(message);
	}

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
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
