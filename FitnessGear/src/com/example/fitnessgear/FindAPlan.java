package com.example.fitnessgear;

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
		
        ListView lv = (ListView) rootView.findViewById(R.id.listView1);
        String[] dataTime = {"Day 1","Day 2","Day 3","Day 4","Day 5","Day 6","Day 7",
				"Day 8","Day 9","Day 10","Day 11","Day 12","Day 13","Day 14",
				"Day 15","Day 16","Day 17","Day 18","Day 19","Day 20","Day 21",
				"Day 22","Day 23","Day 24","Day 25","Day 26","Day 27","Day 28",
				"Day 29","Day 30","Day 31"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,dataTime);
        
        lv.setAdapter(adapter);
		return rootView;
	}
}
