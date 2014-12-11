package com.fitnessgear;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Home extends FragmentActivity {
	
	private Button btn;
	private TabHost tabHost;
	private FragmentTabHost mTabHost;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.fragment_home);
//		tabHost = (TabHost) findViewById(android.R.id.tabhost);
//		tabHost.setup();
//		TabSpec userInformation = tabHost.newTabSpec("tab1");
//		userInformation.setIndicator("User Information");
//		userInformation.setContent(new Intent(this,UserInformation.class));
//		
//		tabHost.addTab(userInformation);
	}
	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		View rootView = inflater.inflate(R.layout.fragment_home, container,
//				false);
//		mTabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
//		mTabHost.setup(getActivity().getApplicationContext(), getChildFragmentManager(), android.R.id.tabcontent);
//		
//		
//		btn = (Button) rootView.findViewById(R.id.button1);
//		btn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(getActivity(),UserInformation.class);
//				startActivity(intent);
//			}
//		});
//		
////		tabHost = (TabHost) rootView.findViewById(R.id.tabhost);
//		tabHost.setup();
//		TabSpec userInformation = tabHost.newTabSpec("tab1");
//		userInformation.setIndicator("User Information");
//		userInformation.setContent(new Intent(getActivity(),UserInformation.class));
//		
//		tabHost.addTab(userInformation);
//		
//		return rootView;
//	}
}
