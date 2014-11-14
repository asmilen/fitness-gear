package com.fitnessgear;

import java.util.ArrayList;

import com.fitnessgear.adapter.ViewPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TrackWorkout extends ActionBarActivity {

	ViewPager pager;
	ViewPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track_workout);
		
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new ViewPagerAdapter(getSupportFragmentManager());
		
		//lay list exercise
		Bundle extras = getIntent().getExtras();
		adapter.myListExercise = extras.getStringArrayList("listExercise");
		
		
		pager.setAdapter(adapter);
	}

}
