package com.fitnessgear;

import java.util.Calendar;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
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

import com.fitnessgear.adapter.ListSetTrackWorkoutAdapter;
import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.model.ListExercisesItem;

public class TrackWorkoutFragment extends Fragment {

	public TrackWorkoutFragment() {
		// TODO Auto-generated constructor stub
	}

	public static TrackWorkoutFragment newInStance(
			ListExercisesItem listExercisesItem, int position) {
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
		final ListExercisesItem item = (ListExercisesItem) getArguments()
				.getSerializable("listExercisesItem");

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
		if (item.getKg().equals("0")) {
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
				getActivity(), Integer.parseInt(item.getSets()),
				item.getRepsmin(), item.getRepsmax());
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
				String KgText = Kgs.getText().toString();

				if (repsText != null && repsText.trim() != "") {

					// Get listview and adapter
					ListView list = (ListView) getView().findViewById(
							R.id.listSetTrackWorkout);
					ListSetTrackWorkoutAdapter adapter = (ListSetTrackWorkoutAdapter) list
							.getAdapter();

					// Set Reps,Kg, and get position
					adapter.setReps(reps.getText().toString());
					adapter.setKgs(Kgs.getText().toString());
					adapter.setOnUpdate(true);
					TextView textViewSetNumber1 = (TextView) getView()
							.findViewById(R.id.textViewSetNumber);

					// Get * from string "SETS: * OF N"
					String setNumber = textViewSetNumber1.getText().toString();
					int position = Integer.parseInt(setNumber.charAt(6) + "");
					textViewSetNumber1.setText(setNumber.substring(0, 6)
							+ (position + 1) + setNumber.substring(7));

					// Update listview
					View row = mylistView.getChildAt(position - 1);
					list.getAdapter().getView(position - 1, row, list);

					// Update to database
					updateDatabase(position, reps.getText().toString(), Kgs
							.getText().toString());
				}
			}

			private void updateDatabase(int position, String reps, String kg) {
				// TODO Auto-generated method stub
				// Get DayID
				Calendar c = Calendar.getInstance();
				String DayID = c.get(Calendar.DAY_OF_MONTH)
						+ c.get(Calendar.MONTH) + c.get(Calendar.YEAR) + "";

				// Insert
				String[] args = new String[] { DayID,
						item.getExerciseID() + "", position + "", reps, kg };
				String sql = "INSERT INTO Log_Exercise VALUES ( ?, ?, ?, ?, ?)";
				
				DataBaseHelper helper = new DataBaseHelper(getActivity());
				SQLiteDatabase db = helper.getWritableDatabase();
				db.rawQuery(sql, args);
			}
		});

		return rootView;
	}

}
