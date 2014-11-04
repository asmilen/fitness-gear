package com.example.fitnessgear;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Setting extends Fragment {

	public Setting() {
		// TODO Auto-generated constructor stub
	}
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View rootView = inflater.inflate(R.layout.fragment_setting, container,false);
	
	return rootView;
}
}
