package com.fitnessgear.adapter;

import com.fitnessgear.SetGoals;
import com.fitnessgear.UpdateBodyStats;
import com.fitnessgear.UserInformation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

	public HomeViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}



	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0) {

        case 0: return UserInformation.newInstance("User Information");
        case 1: return UpdateBodyStats.newInstance("Update Stats");
        case 2: return SetGoals.newInstance("Set Goals");
        default: return UserInformation.newInstance("User Information");
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
