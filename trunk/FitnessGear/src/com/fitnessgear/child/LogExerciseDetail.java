package com.fitnessgear.child;

import java.util.ArrayList;

import com.fitnessgear.Logs;
import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.database.DatabaseUltility;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Get DayID
		setContentView(R.layout.activity_log_exercise_detail);
		Bundle extra = getIntent().getExtras();
		String dayID = extra.getString(Logs.DAYID);
		Toast.makeText(getApplicationContext(), dayID, Toast.LENGTH_LONG).show();
		//Select Database
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		Cursor c = MainActivity.db.rawQuery("Select distinct ExerciseId from Log_Exercise Where Day="+dayID, null);
		while (c.moveToNext()) {
			Toast.makeText(getApplicationContext(), "a", Toast.LENGTH_LONG).show();
			adapter.add(DatabaseUltility.GetColumnValue(c, DatabaseUltility.ExerciseID));
		}
		
		//Set adapter
		ListView listview = (ListView) findViewById(R.id.listView1);
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
