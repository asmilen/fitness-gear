package com.fitnessgear;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.custom.EditTextWithDeleteButton;
import com.fitnessgear.custom.RightDrawableOnTouchListener;
import com.fitnessgear.custom.EditTextWithDeleteButton.TextChangedListener;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ExerciseLibrary extends Fragment {

	private ArrayList<ExercisesItem> myListExercise;
	private ListExercisesAdapter adapter;

	private EditText searchExercise;
	private ListView listFullExercises;

	// The image we are going to use for the Clear button
	private Drawable clearbutton;
	private Drawable searchbutton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_exercise_library,
				container, false);
		listFullExercises = (ListView) rootView
				.findViewById(R.id.listFullExercises);
		searchExercise = (EditText) rootView
				.findViewById(R.id.searchExercise);
		clearbutton = getResources()
				.getDrawable(R.drawable.text_field_clear_btn);
		searchbutton = getResources().getDrawable(R.drawable.search);
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.layout);
		layout.setOnTouchListener(new OnTouchListener()
		{

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				searchExercise.clearFocus();
				handleClearButton();
				hideKeyboard(view);
		        return false;
			}
		});
		// imgCloseButton.setBounds(0, 0,
		// imgCloseButton.getIntrinsicWidth(),imgCloseButton.getIntrinsicHeight());

		getData();
		return rootView;
	}

	public void getData() {
		myListExercise = new ArrayList<ExercisesItem>();

		Cursor listExerciseCursor = MainActivity.db.rawQuery(
				"Select * FROM Exercise Order By ExerciseName", null);
		
		while (listExerciseCursor.moveToNext()) {
			myListExercise.add(new ExercisesItem(DatabaseUltility
					.GetColumnValue(listExerciseCursor, "Workout_Exercise."
							+ DatabaseUltility.ExerciseID), DatabaseUltility
					.GetColumnValue(listExerciseCursor,
							DatabaseUltility.ExerciseName), DatabaseUltility
					.GetColumnValue(listExerciseCursor,
							DatabaseUltility.ExerciseType), DatabaseUltility
					.GetColumnValue(listExerciseCursor,
							DatabaseUltility.MuscleTarget), DatabaseUltility
					.GetColumnValue(listExerciseCursor,
							DatabaseUltility.Equipment),
					DatabaseUltility.GetColumnValue(listExerciseCursor,
							DatabaseUltility.Rating), DatabaseUltility
							.GetColumnValue(listExerciseCursor,
									DatabaseUltility.Image1), DatabaseUltility
							.GetColumnValue(listExerciseCursor,
									DatabaseUltility.Image2), DatabaseUltility
							.GetColumnValue(listExerciseCursor,
									DatabaseUltility.Description)));
		}

		adapter = new ListExercisesAdapter(getActivity(), myListExercise);

		listFullExercises.setAdapter(adapter);

		// searchExerciseName.setHint("Type something...");
		searchExercise.setCompoundDrawablesWithIntrinsicBounds(searchbutton, null,null,null);
		searchExercise.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				handleClearButton();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				adapter.filter(searchExercise.getText().toString()
						.toLowerCase(Locale.getDefault()));
			}
		});

		listFullExercises.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent exerciseDetailIntent = new Intent(getActivity(),
						ExerciseDetail.class);
				exerciseDetailIntent.putExtra("ExerciseID",
						myListExercise.get(position).getExerciseID());
				// exerciseDetailIntent.putExtra("workoutID", workoutID);
				searchExercise.clearFocus();
				startActivity(exerciseDetailIntent);
			}
		});
		
	}
	//Tao ra anh nut clear va anh nut search
	public void handleClearButton() {
		if (searchExercise.getText().toString().equals("")) {
			// remove the clear button
			searchExercise.setCompoundDrawablesWithIntrinsicBounds(searchbutton, null, null, null);
		} else {
			// add clear button
			searchExercise.setCompoundDrawablesWithIntrinsicBounds(null, null, clearbutton, null);
		}
	}
	
	//An ban phim khi bam ra ban ngoai
	protected void hideKeyboard(View view)
	{
	    InputMethodManager in = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
	    in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
