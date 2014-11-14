package com.fitnessgear;

import com.fitnessgear.database.CreateDatabase;
import com.fitnessgear.database.DatabaseUltility;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitnessgear.adapter.GridAdapter;
import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.model.GridItem;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
	private TextView txtGender;
	private TextView txtMainGoal;
	private TextView txtLevelTrain;
	private TextView txtDateCreated;

	private TextView txtTotalWeeks;
	private TextView txtAverageDay;
	private TextView txtArverageTime;
	private TextView txtTotalTime;
	private TextView txtTotalCadio;

	private ArrayList<GridItem> item;

	private CreateDatabase helper;
	private SQLiteDatabase db = null;

	public StartWorkOut() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_start_workout,
				container, false);

		// Khoi Tao Databse
		helper = new CreateDatabase(getActivity());

		grid = (GridView) rootView.findViewById(R.id.listExerciseOnPlan);

		planName = (TextView) rootView.findViewById(R.id.planName);
		author = (TextView) rootView.findViewById(R.id.author);
		txtGender = (TextView) rootView.findViewById(R.id.txtGender);
		txtMainGoal = (TextView) rootView.findViewById(R.id.txtMainGoal);
		txtLevelTrain = (TextView) rootView.findViewById(R.id.txtLevelTrain);
		txtDateCreated = (TextView) rootView.findViewById(R.id.txtDateCreated);

		txtTotalWeeks = (TextView) rootView.findViewById(R.id.txtTotalWeeks);
		txtAverageDay = (TextView) rootView.findViewById(R.id.txtAverageDay);
		txtArverageTime = (TextView) rootView
				.findViewById(R.id.txtArverageTime);
		txtTotalTime = (TextView) rootView.findViewById(R.id.txtTotalTime);
		txtTotalCadio = (TextView) rootView.findViewById(R.id.txtTotalCadio);

		// FrameLayout f = (FrameLayout)
		// getActivity().findViewById(R.id.content_frame);
		// planName.setText("Width: " + f.getMeasuredWidth());
		// author.setText("Heigh: " + f.getMeasuredHeight());

		// Call method
		getData();

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
				intent.putExtra("Day", position);
				startActivity(intent);
			}
		});

		return rootView;
	}

	public void getData() {

		try {
			db = helper.getReadableDatabase();
			Cursor c = db.rawQuery("Select * FROM Plan Where PlanID=1", null);

			ArrayList<String> data = new ArrayList<String>();
			while (c.moveToNext()) {
				// Lam bien dong
				// String s = "[" + c.getInt(c.getColumnIndex("PlanID")) + "] "
				// +
				// "" + c.getString(c.getColumnIndex("PlanName")) + " : " +
				// c.getInt(c.getColumnIndex("MainGoal"));
				// data.add(s);

				// planName.setText(DatabaseUltility.GetColumnValue(c,
				// DatabaseUltility.PlanName)+"");
				planName.setText(c.getString(c.getColumnIndex("PlanName")) + "");
				author.setText(c.getString(c.getColumnIndex("CreatedBy")) + "");
				txtGender.setText(c.getString(c.getColumnIndex("Gender")));
				txtMainGoal.setText(c.getString(c.getColumnIndex("MainGoal")));
				txtLevelTrain.setText(c.getString(c
						.getColumnIndex("FitnessLevel")));
				txtDateCreated.setText(c.getString(c
						.getColumnIndex("DateCreated")));

				txtTotalWeeks.setText(c.getString(c
						.getColumnIndex("TotalWeeks")));
				txtAverageDay.setText(c.getString(c.getColumnIndex("AveDay")));
				txtArverageTime.setText(c.getString(c
						.getColumnIndex("AveWorkoutTime")));
				txtTotalTime.setText(c.getString(c
						.getColumnIndex("TotalTimeAWeek")));
				txtTotalCadio.setText(c.getString(c
						.getColumnIndex("TotalCardioTime")));
			}
		} catch (SQLiteException ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
