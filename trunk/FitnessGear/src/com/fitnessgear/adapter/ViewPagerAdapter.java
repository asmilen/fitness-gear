package com.fitnessgear.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fitnessgear.TrackWorkoutFragment;
import com.fitnessgear.model.ListExercisesItem;
import com.fitnessgear.model.LogExerciseList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

	public ArrayList<ListExercisesItem> myListExercise;
	public LogExerciseList myLogExerciseList;
	public String workoutID;
	
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return TrackWorkoutFragment.newInStance(myListExercise.get(position),position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myListExercise.size();
	}
}
