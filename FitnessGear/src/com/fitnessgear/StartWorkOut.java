package com.fitnessgear;

import com.fitnessgear.child.StartWorkOutDetail;
import com.fitnessgear.database.DatabaseUltility;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitnessgear.adapter.GridAdapter;
import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.model.WorkoutItem;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StartWorkOut extends Fragment {

	private GridView grid;
//	private ListView listWorkout;

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

	private ArrayList<WorkoutItem> item;

//	private SQLiteDatabase db = null;

	public StartWorkOut() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_start_workout,
				container, false);

		Cursor workout = MainActivity.db.rawQuery("SELECT * FROM Workout WHERE PlanID = 1",
				null);
		//Khoi tao ArrayList cac WorkoutItem
		item = new ArrayList<WorkoutItem>();

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
		txtTotalCadio = (TextView) rootView.findViewById(R.id.txtTotalCardio);

		// Call method
		getData();
		//Add value to ArrayList
//		for (int i = 1; i <= Integer.parseInt(txtTotalWeeks.getText()
//				.toString()); i++) {
//			item.add(new WorkoutItem("Week " + i));
			while (workout.moveToNext()) {
				item.add(new WorkoutItem(
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.WorkoutID),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.WorkoutName),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.Description),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.TotalWorkoutTime),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.TotalCardioTime),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.TotalExercises),
						DatabaseUltility.GetColumnValue(workout, DatabaseUltility.TotalSets)));
//						workout.getString(workout.getColumnIndex("WorkoutID")),
//						workout.getString(workout.getColumnIndex("WorkoutName")),
//						workout.getString(workout.getColumnIndex("Description")),
//						workout.getString(workout.getColumnIndex("TotalWorkoutTime")),
//						workout.getString(workout.getColumnIndex("TotalCardioTime")),
//						workout.getString(workout.getColumnIndex("TotalExercises")),
//						workout.getString(workout.getColumnIndex("TotalSets"))));
			}
//		}

		GridAdapter adapter = new GridAdapter(getActivity(), item);

		grid.setAdapter(adapter);
		//Set click for Grid View Start Workout
		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int pos = position;
				Intent intent = new Intent(getActivity(),
						StartWorkOutDetail.class);
				// item.get(position).getWorkoutID();
				intent.putExtra("WorkoutID", item.get(position).getWorkoutID());
				intent.putExtra("TotalWorkoutTime", item.get(position).getTotalWorkoutTime());
				intent.putExtra("TotalCardioTime", item.get(position).getTotalCardioTime());
				intent.putExtra("TotalExercises", item.get(position).getTotalExercises());
				intent.putExtra("TotalSets", item.get(position).getTotalSets());
				intent.putExtra("Day", (position+1)+"");
				intent.putExtra("CreatedBy", author.getText().toString());
				intent.putExtra("ProgramFor", txtGender.getText().toString());
				intent.putExtra("MainGoal", txtMainGoal.getText().toString());
				intent.putExtra("Level", txtLevelTrain.getText().toString());
				startActivity(intent);
			}
		});

		return rootView;
	}

	public void getData() {

		try {
			MainActivity.db = MainActivity.dbHelper.getReadableDatabase();
			Cursor c = MainActivity.db.rawQuery("Select * FROM Plan Where PlanID = 1", null);

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
				planName.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.PlanName));
				author.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.CreatedBy));
				txtGender.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.Gender));
				txtMainGoal.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.MainGoal));
				txtLevelTrain.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.FitnessLevel));
				txtDateCreated.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.DateCreated));

				txtTotalWeeks.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.TotalWeeks));
				txtAverageDay.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.AveDay));
				txtArverageTime.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.AveWorkoutTime));
				txtTotalTime.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.TotalTimeAWeek));
				txtTotalCadio.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.TotalCardioTime));
			}
		} catch (SQLiteException ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		} catch (Exception ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
