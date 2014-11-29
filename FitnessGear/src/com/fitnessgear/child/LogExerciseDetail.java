package com.fitnessgear.child;

import java.util.ArrayList;

import com.fitnessgear.Logs;
import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.adapter.LogExerciseAdapter;
import com.fitnessgear.database.DatabaseUltility;

import com.fitnessgear.model.LogExerciseItem;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LogExerciseDetail extends Activity {

	private ArrayList<LogExerciseItem> myListExerciseDetail;
	private ArrayList<Integer> myListExerciseID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get DayID
		setContentView(R.layout.activity_log_exercise_detail);
		Bundle extra = getIntent().getExtras();
		String dayID = extra.getString(Logs.DAYID);

		// Select Database
		// Load list exercise tu database
		try {

			// Khoi tao ArrayList tu LogExerciseItem class
			myListExerciseDetail = new ArrayList<LogExerciseItem>();
			myListExerciseID = new ArrayList<Integer>();

			Cursor listExerciseCursor = MainActivity.db.rawQuery("Select * "
					+ "FROM Log_Exercise Where Day = " + dayID, null);

			while (listExerciseCursor.moveToNext()) {
				myListExerciseDetail.add(new LogExerciseItem(dayID,
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.ExerciseID), DatabaseUltility
								.GetIntColumnValue(listExerciseCursor,
										DatabaseUltility.Sets),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.Reps), DatabaseUltility
								.GetIntColumnValue(listExerciseCursor,
										DatabaseUltility.Kg), DatabaseUltility
								.GetIntColumnValue(listExerciseCursor,
										DatabaseUltility.Interval)));
			}
			listExerciseCursor = MainActivity.db.rawQuery(
					"Select Distinct ExerciseID from Log_Exercise Where Day= "
							+ dayID, null);

			while (listExerciseCursor.moveToNext()) {
				myListExerciseID.add(DatabaseUltility.GetIntColumnValue(
						listExerciseCursor, DatabaseUltility.ExerciseID));
			}

		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}

		// adapter
		LogExerciseAdapter adapter = new LogExerciseAdapter(
				getApplicationContext(), myListExerciseDetail, myListExerciseID);

		// Set adapter
		ListView listview = (ListView) findViewById(R.id.listViewSet);
		listview.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_exercise_detail, menu);
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
