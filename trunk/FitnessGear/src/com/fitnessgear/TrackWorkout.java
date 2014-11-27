package com.fitnessgear;

import java.util.ArrayList;
import java.util.Calendar;

import com.fitnessgear.adapter.ViewPagerAdapter;
import com.fitnessgear.model.ListExercisesItem;
import com.fitnessgear.model.LogExerciseItem;
import com.fitnessgear.model.LogExerciseList;
import com.google.gson.Gson;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TrackWorkout extends ActionBarActivity {

	ViewPager pager;
	ViewPagerAdapter adapter;
	PagerTitleStrip pagerTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track_workout);
		// Set Back button on activity
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		pager = (ViewPager) findViewById(R.id.pager);
		pagerTitle = (PagerTitleStrip) findViewById(R.id.pagerTitle);
		pagerTitle.setTextSize(1, 31);
		adapter = new ViewPagerAdapter(getSupportFragmentManager());

		// lay list exercise
		Bundle extras = getIntent().getExtras();
		adapter.myListExercise = (ArrayList<ListExercisesItem>) extras
				.getSerializable("listExercise");
		adapter.workoutID = extras.getString("workoutID");

		// Tao list Log_exercise
		adapter.myLogExerciseList = new LogExerciseList();
		Calendar c = Calendar.getInstance();
		String DayID = c.get(Calendar.DAY_OF_MONTH) + ""
				+ c.get(Calendar.MONTH) + "" + c.get(Calendar.YEAR);

		for (ListExercisesItem item : adapter.myListExercise) {
			int nSet = Integer.valueOf(item.getSets());
			for (int i = 1; i <= nSet; i++) {
				adapter.myLogExerciseList.add(new LogExerciseItem(DayID,
						Integer.valueOf(item.getExerciseID()), i, 0, 0, 0));
			}
		}

		// Tao SharedPreference
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Editor prefsEditor = settings.edit();
		Gson gson = new Gson();
		String json = gson.toJson(adapter.myLogExerciseList);
		prefsEditor.putString("logexerciselist", json);
		prefsEditor.commit();

		// Set adapter
		pager.setAdapter(adapter);
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
		// Back activity
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
