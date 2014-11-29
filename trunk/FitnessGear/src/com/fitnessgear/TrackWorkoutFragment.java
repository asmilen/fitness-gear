package com.fitnessgear;

import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.adapter.ListSetTrackWorkoutAdapter;
import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TrackWorkoutFragment extends Fragment {

	public TrackWorkoutFragment() {
		// TODO Auto-generated constructor stub
	}

	public static TrackWorkoutFragment newInStance(
			ExercisesItem exercisesItem, int position) {
		TrackWorkoutFragment myTrack = new TrackWorkoutFragment();

		Bundle bundle = new Bundle();
		bundle.putSerializable("listExercisesItem", exercisesItem);
		bundle.putString("position", position + "");
		myTrack.setArguments(bundle);
		return myTrack;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_track_workout,
				container, false);

		// Set ID
		TextView textviewtargetSet = (TextView) rootView
				.findViewById(R.id.textViewTargetSets);
		TextView textviewtotalReps = (TextView) rootView
				.findViewById(R.id.textViewTotalReps);
		TextView textViewEName = (TextView) rootView
				.findViewById(R.id.textViewEName);
		TextView textViewpostion = (TextView) rootView
				.findViewById(R.id.textViewPosition);

		ImageView img1 = (ImageView) rootView.findViewById(R.id.imageView1);
		ImageView img2 = (ImageView) rootView.findViewById(R.id.imageView2);

		ListView mylistView = (ListView) rootView.findViewById(R.id.listSet);

		// Get arguments
		String position = getArguments().getString("position");
		ExercisesItem item = (ExercisesItem) getArguments()
				.getSerializable("listExercisesItem");

		textViewpostion.setText(position);

		textViewEName.setText(item.getExerciseName());

		// Custom Listview
		try {
			ListSetTrackWorkoutAdapter adapter = new ListSetTrackWorkoutAdapter(
					getActivity(),item.getSets(),item.getRepsmin(), item.getRepsmax());
			mylistView.setAdapter(adapter);
		} catch (Exception ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		}

		return rootView;
	}
}
