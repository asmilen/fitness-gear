package com.fitnessgear.adapter;

import java.util.ArrayList;

import com.fitnessgear.TrackWorkoutFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

	public ArrayList<String> myListExercise;
	
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return TrackWorkoutFragment.newInStance(myListExercise.get(position));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myListExercise.size();
	}

}
