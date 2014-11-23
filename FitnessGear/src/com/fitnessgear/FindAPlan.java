package com.fitnessgear;


import com.fitnessgear.adapter.ListSetTrackWorkoutAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FindAPlan extends Fragment {

	public FindAPlan() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_find_a_plan, container, false);
		
        ListView lv = (ListView) rootView.findViewById(R.id.listSet);
        ListSetTrackWorkoutAdapter adapter = new ListSetTrackWorkoutAdapter(
				getActivity(),"3","4","5");
        lv.setAdapter(adapter);
		return rootView;
	}
}
