package com.fitnessgear.child;

import java.util.ArrayList;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fitnessgear.R;
import com.fitnessgear.adapter.ViewPagerAdapter;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.model.LogExerciseItem;
import com.fitnessgear.model.LogExerciseList;
import com.google.gson.Gson;

public class TrackWorkout extends FragmentActivity {

	ViewPager pager;
	ViewPagerAdapter adapter;

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
		adapter = new ViewPagerAdapter(getSupportFragmentManager());

		// lay list exercise
		Bundle extras = getIntent().getExtras();
		adapter.myListExercise = (ArrayList<ExercisesItem>) extras
				.getSerializable("listExercise");
		adapter.workoutID = extras.getString("workoutID");

		// Tao list Log_exercise
		adapter.myLogExerciseList = new LogExerciseList();
		String DayID = DatabaseUltility.getDayID();
		for (ExercisesItem item : adapter.myListExercise) {
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

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		// Set adapter
		pager.setAdapter(adapter);

	}

	private void setHasOptionsMenu(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		new AlertDialog.Builder(this)
				.setTitle("Go Back")
				.setMessage(
						"Are you sure you want to go back? All data will be lost")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// continue with delete
								TrackWorkout.this.finish();
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// do nothing
								dialog.cancel();
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.track_workout_savetodatabase, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();

		if (id == R.id.trackworkoutsave) {
			new AlertDialog.Builder(TrackWorkout.this)
					.setTitle("Finish Editing")
					.setMessage(
							"Are you sure you want to finish tracking workout?")
					.setPositiveButton(
							getResources().getString(R.string.fisish_editing),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// continue with delete
									try {
										SharedPreferences settings = PreferenceManager
												.getDefaultSharedPreferences(getApplicationContext());
										Gson gson = new Gson();
										String json = settings.getString(
												"logexerciselist", "");
										LogExerciseList mylist = gson.fromJson(
												json, LogExerciseList.class);
										DatabaseUltility
												.UpdateToLogExercise(mylist);
										TrackWorkout.this.finish();
									} catch (Exception ex) {
										// TODO: handle exception
										Toast.makeText(getApplicationContext(), ex.getMessage()	, Toast.LENGTH_LONG).show();
									}
								}
							})
					.setNegativeButton(
							getResources().getString(R.string.continue_editing),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// do nothing
									dialog.cancel();
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();
		}
		if (id == android.R.id.home) {
			new AlertDialog.Builder(this)
					.setTitle("Go Back")
					.setMessage(
							"Are you sure you want to go back? All data will be lost")
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// continue with delete
									TrackWorkout.this.finish();
								}
							})
					.setNegativeButton(android.R.string.no,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// do nothing
									dialog.cancel();
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
