package com.fitnessgear;

import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.adapter.ListFilterAdapter;
import com.fitnessgear.child.ExerciseDetail;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.model.FilterItem;

public class ExerciseLibrary extends Fragment {

	private ArrayList<ExercisesItem> myListExercise;
	private ListExercisesAdapter adapter;
	private ArrayList<FilterItem> listFilterData;
	private ArrayList<String> listTypes;
	private ArrayList<String> listMuscles;
	private ArrayList<String> listEquipments;

	private EditText searchExercise;
	private ListView listFullExercises;
	private ListView listFilterExercises;

	// The image we are going to use for the Clear button
	private Drawable clearbutton;
	private Drawable searchbutton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_exercise_library,
				container, false);
		
		listFilterExercises = (ListView) rootView.findViewById(R.id.listFilterExercises);
		
		listFullExercises = (ListView) rootView
				.findViewById(R.id.listFullExercises);
		searchExercise = (EditText) rootView.findViewById(R.id.searchExercise);
		clearbutton = getResources().getDrawable(
				R.drawable.text_field_clear_btn);
		searchbutton = getResources().getDrawable(R.drawable.search);
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.layout);
		layout.setOnTouchListener(new OnTouchListener() {

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
		//Create List Filter
				listFilterData = new ArrayList<FilterItem>();
				listFilterData.add(new FilterItem("Exercise Type","All Types"));
				listFilterData.add(new FilterItem("Muscle Group","All Muscles"));
				listFilterData.add(new FilterItem("Equipment","All Equipments"));

				final ListFilterAdapter filterAdapter = new ListFilterAdapter(getActivity(),
						listFilterData);
				listFilterExercises.setAdapter(filterAdapter);
				
				final Cursor exerciseType = MainActivity.db.rawQuery("Select * FROM ExerciseType", null);
				final Cursor exerciseMuscle = MainActivity.db.rawQuery("Select * FROM Muscles", null);
				final Cursor exerciseEquipment = MainActivity.db.rawQuery("Select * FROM Equipment", null);
				listTypes = new ArrayList<String>();
				listMuscles = new ArrayList<String>();
				listEquipments = new ArrayList<String>();
				while(exerciseType.moveToNext()){
					listTypes.add(DatabaseUltility.GetColumnValue(exerciseType, DatabaseUltility.ExerciseTypeName));
				}
				while(exerciseMuscle.moveToNext()){
					listMuscles.add(DatabaseUltility.GetColumnValue(exerciseMuscle, DatabaseUltility.MuscleName));
				}
				while(exerciseEquipment.moveToNext()){
					listEquipments.add(DatabaseUltility.GetColumnValue(exerciseEquipment, DatabaseUltility.EquipmentName));
				}
				
				final ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listTypes);
				final ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listMuscles);
				final ArrayAdapter<String> mainGoalAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listEquipments);
				listFilterExercises.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							new AlertDialog.Builder(getActivity())
						    .setTitle("Exercise Type")
						    .setSingleChoiceItems(levelAdapter, 0, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();
									listFilterData.get(0).setValueFilter(listTypes.get(((AlertDialog)dialog).getListView().getCheckedItemPosition()).toString());
									filterAdapter.notifyDataSetChanged();
									if(listFilterData.get(0).getValueFilter().toString().toLowerCase(Locale.getDefault()).equalsIgnoreCase("All Types")){
//										adapter = new List
									}
									else{
										adapter.filter(listFilterData.get(0).getValueFilter().toString().toLowerCase(Locale.getDefault()));
									}
								}
							})
						     .show();
							break;
						case 1:
							new AlertDialog.Builder(getActivity())
						    .setTitle("Gender")
						    .setSingleChoiceItems(genderAdapter, 0, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();
									listFilterData.get(1).setValueFilter(listMuscles.get(((AlertDialog)dialog).getListView().getCheckedItemPosition()).toString());
									filterAdapter.notifyDataSetChanged();
								}
							})
						     .show();
							break;
						case 2:
							new AlertDialog.Builder(getActivity())
						    .setTitle("Main Goal")
						    .setSingleChoiceItems(mainGoalAdapter, 0, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();
									listFilterData.get(2).setValueFilter(listEquipments.get(((AlertDialog)dialog).getListView().getCheckedItemPosition()).toString());
									filterAdapter.notifyDataSetChanged();
								}
							})
						     .show();
							break;

						default:
							break;
						}				
					}
				});
		
		//Create List Exercises
		myListExercise = new ArrayList<ExercisesItem>();
		Cursor listExerciseCursor = MainActivity.db.rawQuery(
				"Select * FROM Exercise", null);

		while (listExerciseCursor.moveToNext()) {
			myListExercise.add(new ExercisesItem(DatabaseUltility
					.GetIntColumnValue(listExerciseCursor, "Workout_Exercise."
							+ DatabaseUltility.ExerciseID), DatabaseUltility
					.GetColumnValue(listExerciseCursor,
							DatabaseUltility.ExerciseName), DatabaseUltility
					.GetIntColumnValue(listExerciseCursor,
							DatabaseUltility.ExerciseType), DatabaseUltility
					.GetIntColumnValue(listExerciseCursor,
							DatabaseUltility.MuscleTarget), DatabaseUltility
					.GetIntColumnValue(listExerciseCursor,
							DatabaseUltility.Equipment), DatabaseUltility
					.GetFloatColumnValue(listExerciseCursor,
							DatabaseUltility.Rating),
					DatabaseUltility.GetColumnValue(listExerciseCursor,
							DatabaseUltility.Image1), DatabaseUltility
							.GetColumnValue(listExerciseCursor,
									DatabaseUltility.Image2), DatabaseUltility
							.GetColumnValue(listExerciseCursor,
									DatabaseUltility.Description)));
		}

		adapter = new ListExercisesAdapter(getActivity(), myListExercise);

		listFullExercises.setAdapter(adapter);

		searchExercise.setHint("Type Exercise Name");
		searchExercise.setCompoundDrawablesWithIntrinsicBounds(searchbutton,
				null, null, null);
		searchExercise.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
//				handleClearButton();
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
//				searchExercise.clearFocus();
				startActivity(exerciseDetailIntent);
			}
		});

	}

	// Tao ra anh nut clear va anh nut search
	public void handleClearButton() {
		if (searchExercise.getText().toString().equals("")) {
			// remove the clear button
			searchExercise.setCompoundDrawablesWithIntrinsicBounds(
					searchbutton, null, null, null);
		} else {
			// add clear button
			searchExercise.setCompoundDrawablesWithIntrinsicBounds(null, null,
					clearbutton, null);
		}
	}

	// An ban phim khi bam ra ban ngoai
	protected void hideKeyboard(View view) {
		InputMethodManager in = (InputMethodManager) getActivity()
				.getSystemService(getActivity().INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
