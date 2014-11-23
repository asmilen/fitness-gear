package com.fitnessgear.adapter;

import java.util.ArrayList;

import com.fitnessgear.TrackWorkoutFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager.PageTransformer;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

	public ArrayList<String> myListExercise;
	private PagerTitleStrip pagerTitle;
	
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
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		
		return "Exercise " + (position+1);
	}

}