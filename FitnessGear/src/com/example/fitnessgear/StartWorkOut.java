package com.example.fitnessgear;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.fitnessgear.adapter.GridAdapter;
import com.example.fitnessgear.model.GridItem;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class StartWorkOut extends Fragment {

	private GridView grid;
	private TextView planName;
	private TextView author;
	private ArrayList<GridItem> item;

	public StartWorkOut() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_start_workout,
				container, false);
		
		grid = (GridView) rootView.findViewById(R.id.listExerciseOnPlan);

		planName = (TextView) rootView.findViewById(R.id.planName);
		author = (TextView) rootView.findViewById(R.id.author);
		
		FrameLayout f = (FrameLayout) getActivity().findViewById(R.id.content_frame);
		planName.setText("Width: " + f.getMeasuredWidth());
		author.setText("Heigh: " + f.getMeasuredHeight());

		Calendar cal = Calendar.getInstance();
		int numOfDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		item = new ArrayList<GridItem>();
		for (int i = 1; i <= numOfDay; i++) {
			item.add(new GridItem("Day " + i, "Chest, Triceps and Cavle " + i));
		}

		GridAdapter adapter = new GridAdapter(getActivity(), item);

		grid.setAdapter(adapter);

		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						StartWorkOutDetail.class);
				startActivity(intent);
			}
		});

		return rootView;
	}
}
