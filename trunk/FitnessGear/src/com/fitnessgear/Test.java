package com.fitnessgear;

import com.fitnessgear.adapter.ViewPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

public class Test extends ActionBarActivity {
	
	ViewPager pager;
	ViewPagerAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new ViewPagerAdapter(getSupportFragmentManager());
		
		//lay list exercise
		Bundle extras = getIntent().getExtras();
		adapter.myListExercise = extras.getStringArrayList("listExercise");
		
		
		pager.setAdapter(adapter);
	}
}
