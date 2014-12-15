package com.fitnessgear.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fitnessgear.LogBodyStats;
import com.fitnessgear.LogNote;
import com.fitnessgear.child.LogExerciseDetail;

public class LogViewPagerAdapter extends FragmentStatePagerAdapter {

	public LogViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0) {

        case 0: return LogExerciseDetail.newInstance("User Information");
        case 1: return LogNote.newInstance("Update Stats");
        case 2: return LogBodyStats.newInstance("Set Goals");
        default: return LogExerciseDetail.newInstance("User Information");
        }
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	private static final String[] titles = { "User Information", "Update Stats", "Set Goals" };
	 
	//...
	 
	@Override
	public CharSequence getPageTitle(int position) {
	    return titles[position];
	}
	
}
