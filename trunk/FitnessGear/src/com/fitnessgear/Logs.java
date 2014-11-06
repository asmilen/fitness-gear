package com.fitnessgear;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Logs extends Fragment {

	public Logs() {
		// TODO Auto-generated constructor stub
	}
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View rootView = inflater.inflate(R.layout.fragment_logs, container, false);
	
	return rootView;
}
}
