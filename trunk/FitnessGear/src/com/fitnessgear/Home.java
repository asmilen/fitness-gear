package com.fitnessgear;

import com.fitnessgear.adapter.HomeViewPagerAdapter;
import com.fitnessgear.adapter.ViewPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class Home extends Fragment {
	
	ViewPager pager;
	HomeViewPagerAdapter adapter;
	PagerTabStrip pagerTab;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		try{
			pager = (ViewPager) rootView.findViewById(R.id.viewPager);
			pagerTab = (PagerTabStrip) rootView.findViewById(R.id.pagerTabStrip);
			adapter = new HomeViewPagerAdapter(getActivity().getSupportFragmentManager());
			pagerTab.setTabIndicatorColor(Color.DKGRAY);
			pagerTab.setBackgroundColor(Color.GRAY);
			pagerTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
			pager.setAdapter(adapter);
		}
		catch(Exception ex){
			Toast.makeText(getActivity(), "" + ex, Toast.LENGTH_LONG).show();
		}
		return rootView;
	}
}
