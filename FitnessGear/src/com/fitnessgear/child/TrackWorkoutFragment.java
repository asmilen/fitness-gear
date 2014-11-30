package com.fitnessgear.child;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessgear.R;
import com.fitnessgear.R.id;
import com.fitnessgear.R.layout;
import com.fitnessgear.adapter.ListSetTrackWorkoutAdapter;
import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.model.LogExerciseItem;
import com.fitnessgear.model.LogExerciseList;
import com.google.gson.Gson;

public class TrackWorkoutFragment extends Fragment {
	
	
	
	public TrackWorkoutFragment() {
		// TODO Auto-generated constructor stub
	}

	public static TrackWorkoutFragment newInStance(
			ExercisesItem listExercisesItem, int position) {
		TrackWorkoutFragment myTrack = new TrackWorkoutFragment();

		Bundle bundle = new Bundle();
		bundle.putSerializable("listExercisesItem", listExercisesItem);
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

		// Get ID
		TextView textviewtargetSet = (TextView) rootView
				.findViewById(R.id.textViewTargetSets);
		TextView textviewtotalReps = (TextView) rootView
				.findViewById(R.id.textViewTotalReps);
		TextView textViewEName = (TextView) rootView
				.findViewById(R.id.textViewEName);
		TextView textViewpostion = (TextView) rootView
				.findViewById(R.id.textViewPosition);
		TextView textViewSetNumber = (TextView) rootView
				.findViewById(R.id.textViewSetNumber);
		TextView textViewX = (TextView) rootView.findViewById(R.id.textViewX);
		EditText editTextKg = (EditText) rootView.findViewById(R.id.editTextKg);

		ImageView img1 = (ImageView) rootView.findViewById(R.id.imageViewTick);
		ImageView img2 = (ImageView) rootView.findViewById(R.id.imageView2);

		final ListView mylistView = (ListView) rootView
				.findViewById(R.id.listSetTrackWorkout);

		// Get arguments
		String position = getArguments().getString("position");
		final ExercisesItem item = (ExercisesItem) getArguments()
				.getSerializable("listExercisesItem");
		final int eID = item.getExerciseID();

		// Set text and image
		textViewpostion.setText(position);
		textViewEName.setText(item.getExerciseName());
		textviewtargetSet.setText("TARGET SETS: " + item.getSets());
		textViewSetNumber.setText("SETS: 1 OF " + item.getSets());

		int TotalReps = Integer.valueOf(item.getRepsmin())
				* Integer.valueOf(item.getSets());
		textviewtotalReps.setText(TotalReps + " TOTAL REPS");

		// Decode String image1
		String image1 = item.getImg1();
		byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
		Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,
				decodedString.length);

		// Set image by bitmap
		img1.setImageBitmap(decodedByte);

		// Decode String image2
		String image2 = item.getImg2();
		decodedString = Base64.decode(image2, Base64.DEFAULT);
		decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,
				decodedString.length);

		// Set image by bitmap
		img2.setImageBitmap(decodedByte);

		// Hide edittext Kg if necessary
		if (item.getKg() == 0) {
			editTextKg.setVisibility(View.GONE);
			textViewX.setVisibility(View.GONE);
		}

		// Hide keyboard when touched outside edittext area
		if (!(rootView instanceof EditText)) {

			rootView.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					inputMethodManager.hideSoftInputFromWindow(getActivity()
							.getCurrentFocus().getWindowToken(), 0);

					return false;
				}

			});
		}
		// Custom Listview
		ListSetTrackWorkoutAdapter adapter = new ListSetTrackWorkoutAdapter(
				getActivity(), item.getSets(), item.getRepsmin(),
				item.getRepsmax(), item.getExerciseID());
		mylistView.setAdapter(adapter);

		// Button saveset

	
		Button b = (Button) rootView.findViewById(R.id.buttonSaveSet);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Get value from view
				EditText reps = (EditText) getView().findViewById(
						R.id.editTextReps);
				String repsText = reps.getText().toString();

				EditText Kgs = (EditText) getView().findViewById(
						R.id.editTextKg);

				if (!repsText.trim().equals("")) {
					// Set Reps,Kg, and get position
					TextView textViewSetNumber1 = (TextView) getView()
							.findViewById(R.id.textViewSetNumber);

					// Get * from string "SETS: * OF N"
					String setNumber = textViewSetNumber1.getText().toString();
					int position = Integer.parseInt(setNumber.charAt(6) + "");
					int total = Integer.parseInt(setNumber.charAt(11) + "");
					if (position <= total) {
						if (position < total)
							textViewSetNumber1.setText(setNumber
									.substring(0, 6)
									+ (position + 1)
									+ setNumber.substring(7));

						// Get listview and adapter
						ListView list = (ListView) getView().findViewById(
								R.id.listSetTrackWorkout);

						// Update to list
						updatelist(position, reps.getText().toString(), Kgs
								.getText().toString(), eID);

						// Update listview
						View row = mylistView.getChildAt(position - 1);
						list.getAdapter().getView(position - 1, row, list);
					}
				}
			}

			private void updatelist(int Set, String reps, String kg,
					int ExerciseID) {
				// TODO Auto-generated method stub

				// Get sharedpreference
				try {
					SharedPreferences settings = PreferenceManager
							.getDefaultSharedPreferences(getActivity());
					Gson gson = new Gson();
					String json = settings.getString("logexerciselist", "");
					LogExerciseList mylist = gson.fromJson(json,
							LogExerciseList.class);

					// Lay ra vi tri can update
					ArrayList<LogExerciseItem> list = mylist
							.getMyLogExerciseList();
					int i = 0;
					LogExerciseItem Tempitem = new LogExerciseItem(0, 0, 0);
					for (i = 0; i < list.size(); i++) {
						Tempitem = list.get(i);
						if (Tempitem.getExerciseID() == ExerciseID
								&& Tempitem.getSets() == Set)
							break;
					}

					// Update
					int repsValue = 0, kgsValue = 0;
					try {
						repsValue = Integer.parseInt(reps);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						kgsValue = Integer.parseInt(kg);
					} catch (Exception e) {
						// TODO: handle exception
					}

					LogExerciseItem updatedItem = new LogExerciseItem(Tempitem
							.getDay(), ExerciseID, Set, repsValue, kgsValue, 0);
					mylist.updateItem(i, updatedItem);

					// Put to Shared Preference
					Editor prefsEditor = settings.edit();
					gson = new Gson();
					json = gson.toJson(mylist);
					prefsEditor.putString("logexerciselist", json);
					prefsEditor.commit();
				} catch (Exception ex) {
					Toast.makeText(getActivity(), ex.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}
		});

		return rootView;
	}

}
