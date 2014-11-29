package com.fitnessgear;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.child.ExerciseDetail;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ExerciseLibrary extends Fragment {

	private ArrayList<ExercisesItem> myListExercise;
	private ListExercisesAdapter adapter;

	private ListView listFullExercises;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_exercise_library,
				container, false);
		// listFullExercises = (ListView)
		// rootView.findViewById(R.id.listFullExercises);
		getData();
		return rootView;
	}

	public void getData() {
		try {
			myListExercise = new ArrayList<ExercisesItem>();

			Cursor listExerciseCursor = MainActivity.db.rawQuery(
					"Select * FROM Exercise Order By ExerciseName", null);
			int i = 0;
			// Toast.makeText(getApplicationContext(), workoutId,
			// Toast.LENGTH_LONG).show();
			while (listExerciseCursor.moveToNext()) {
				myListExercise.add(new ExercisesItem(DatabaseUltility
						.GetIntColumnValue(listExerciseCursor,
								"Workout_Exercise."
										+ DatabaseUltility.ExerciseID),
						DatabaseUltility.GetColumnValue(listExerciseCursor,
								DatabaseUltility.ExerciseName),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.ExerciseType),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.MuscleTarget),
						DatabaseUltility.GetIntColumnValue(listExerciseCursor,
								DatabaseUltility.Equipment), DatabaseUltility
								.GetFloatColumnValue(listExerciseCursor,
										DatabaseUltility.Rating),
						DatabaseUltility.GetColumnValue(listExerciseCursor,
								DatabaseUltility.Image1), DatabaseUltility
								.GetColumnValue(listExerciseCursor,
										DatabaseUltility.Image2),
						DatabaseUltility.GetColumnValue(listExerciseCursor,
								DatabaseUltility.Description)));
			}

			adapter = new ListExercisesAdapter(getActivity(), myListExercise);

			listFullExercises.setAdapter(adapter);

			listFullExercises.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent exerciseDetailIntent = new Intent(getActivity(),
							ExerciseDetail.class);
					exerciseDetailIntent.putExtra("ExerciseID", myListExercise
							.get(position).getExerciseID());
					// exerciseDetailIntent.putExtra("workoutID", workoutID);
					startActivity(exerciseDetailIntent);
				}
			});
		} catch (Exception ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
