package com.fitnessgear;

import java.text.DecimalFormat;
import java.util.Calendar;

import com.fitnessgear.adapter.HomeViewPagerAdapter;
import com.fitnessgear.adapter.ViewPagerAdapter;
import com.fitnessgear.database.DatabaseUltility;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class Home extends Fragment {

	private ViewPager pager;
	private HomeViewPagerAdapter adapter;
	private PagerTabStrip pagerTab;
	private LinearLayout homeLayout;

	private ImageView avaImage;
	private static TextView tvUserName;
	private static TextView tvAge;
	private static TextView tvGender;
	private static TextView tvWeight;
	private static TextView tvHeight;
	private static TextView tvBodyFat;
	private static TextView tvBMI;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		//Hide keyboard when click layout
		homeLayout = (LinearLayout) rootView.findViewById(R.id.homeLayout);
		homeLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				hideKeyboard(view);
				UserInformation.getData();
				return false;
			}
		});
		try {
			pager = (ViewPager) rootView.findViewById(R.id.viewPager);
			pagerTab = (PagerTabStrip) rootView
					.findViewById(R.id.pagerTabStrip);
			adapter = new HomeViewPagerAdapter(getChildFragmentManager());
			pagerTab.setTabIndicatorColor(Color.BLUE);
			pagerTab.setBackgroundColor(Color.CYAN);
			pagerTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
			pager.setAdapter(adapter);
			pagerTab.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					UserInformation.getData();
					return false;
				}
			});
		} catch (Exception ex) {
			Toast.makeText(getActivity(), "" + ex, Toast.LENGTH_LONG).show();
		}
	
		avaImage = (ImageView) rootView.findViewById(R.id.avaImage);
		tvUserName = (TextView) rootView.findViewById(R.id.tvUserName);
		tvAge = (TextView) rootView.findViewById(R.id.tvAge);
		tvGender = (TextView) rootView.findViewById(R.id.tvGender);
		tvWeight = (TextView) rootView.findViewById(R.id.tvWeight);
		tvHeight = (TextView) rootView.findViewById(R.id.tvHeight);
		tvBodyFat = (TextView) rootView.findViewById(R.id.tvBodyFat);
		tvBMI = (TextView) rootView.findViewById(R.id.tvBMI);
		getData();
		
		return rootView;
	}
	
	public static void getData(){
		Cursor user = MainActivity.db.rawQuery("SELECT * FROM User Where UserID = 1", null);
		while(user.moveToNext()){
			tvUserName.setText(DatabaseUltility.GetColumnValue(user, DatabaseUltility.UserName));
			String Year = DatabaseUltility.GetColumnValue(user, DatabaseUltility.DateOfBirth).substring(6);
			int age = Calendar.getInstance().get(Calendar.YEAR) - Integer.valueOf(Year);
			tvAge.setText("Age: " + age);
			tvGender.setText("Gender: " + DatabaseUltility.GetColumnValue(user, DatabaseUltility.Gender));
			tvWeight.setText("Weight: " + DatabaseUltility.GetFloatColumnValue(user, DatabaseUltility.Weight));
			tvHeight.setText("Height: " + DatabaseUltility.GetFloatColumnValue(user, DatabaseUltility.Height));
			tvBodyFat.setText("Body Fat: " + DatabaseUltility.GetFloatColumnValue(user, DatabaseUltility.BodyFat));
			float BMI = 0;
			if(DatabaseUltility.GetFloatColumnValue(user, DatabaseUltility.Weight) != 0 && DatabaseUltility.GetFloatColumnValue(user, DatabaseUltility.Height) != 0){
				float weight = DatabaseUltility.GetFloatColumnValue(user, DatabaseUltility.Weight);
				float height = DatabaseUltility.GetFloatColumnValue(user, DatabaseUltility.Height)/100;
				BMI =  weight/(height*height);
			}
			DecimalFormat form = new DecimalFormat("0.00");
			tvBMI.setText("BMI: " + form.format(BMI));
		}
	}

	// An ban phim khi bam ra ben ngoai
	protected void hideKeyboard(View view) {
		InputMethodManager in = (InputMethodManager) getActivity()
				.getSystemService(getActivity().INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
