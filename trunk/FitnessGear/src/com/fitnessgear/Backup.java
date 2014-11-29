package com.fitnessgear;




import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Backup extends Fragment {

	public Backup() {
		// TODO Auto-generated constructor stub
	}
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View rootView = inflater.inflate(R.layout.fragment_backup, container, false);
	
	return rootView;
}
}
