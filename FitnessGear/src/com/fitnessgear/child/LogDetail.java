package com.fitnessgear.child;

import com.fitnessgear.Logs;
import com.fitnessgear.R;
import com.fitnessgear.R.id;
import com.fitnessgear.R.layout;
import com.fitnessgear.R.menu;
import com.fitnessgear.adapter.HomeViewPagerAdapter;
import com.fitnessgear.adapter.LogViewPagerAdapter;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

public class LogDetail extends FragmentActivity {

	private ViewPager pager;
	private LogViewPagerAdapter adapter;
	private PagerTabStrip pagerTab;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_detail);
		ActionBar actionBar = getActionBar();
		// Set back button on action Bar
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		Bundle bund = getIntent().getExtras();
		
		pager = (ViewPager) findViewById(R.id.logPager);
		pagerTab = (PagerTabStrip) 
				findViewById(R.id.logPagerTabStrip);
		adapter = new LogViewPagerAdapter(getSupportFragmentManager());
		adapter.dayID = bund.getString(Logs.DAYID);
		pagerTab.setTabIndicatorColor(getResources().getColor(R.color.user_name));
		pagerTab.setBackgroundColor(getResources().getColor(R.color.child_background));
		pagerTab.setTextColor(getResources().getColor(R.color.user_name));
		pagerTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		pager.setAdapter(adapter);
		pager.setCurrentItem(1);
	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.log_detail, menu);
//		return true;
//	}
//
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}