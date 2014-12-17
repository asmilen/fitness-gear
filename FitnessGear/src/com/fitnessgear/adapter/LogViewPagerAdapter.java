package com.fitnessgear.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fitnessgear.child.LogBodyStats;
import com.fitnessgear.child.LogExerciseDetail;
import com.fitnessgear.child.LogNote;

public class LogViewPagerAdapter extends FragmentStatePagerAdapter {

	public static String dayID;
	public LogViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0) {

        case 0: return LogNote.newInstance(dayID);
        case 1: return LogExerciseDetail.newInstance(dayID);
        case 2: return LogBodyStats.newInstance(dayID);
        default: return LogExerciseDetail.newInstance(dayID);
        }
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	private static final String[] titles = { "Note", "Exercise", "Body Stats" };
	 
	//...
	 
	@Override
	public CharSequence getPageTitle(int position) {
	    return titles[position];
	}
	
}
