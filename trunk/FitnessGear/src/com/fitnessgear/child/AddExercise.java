package com.fitnessgear.child;

import java.util.ArrayList;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.R.id;
import com.fitnessgear.R.layout;
import com.fitnessgear.R.menu;
import com.fitnessgear.adapter.ListExercisesAdapter;
import com.fitnessgear.adapter.ListFilterAdapter;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.EquipmentItem;
import com.fitnessgear.model.ExerciseTypeItem;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.model.FilterItem;
import com.fitnessgear.model.MuscleTargetItem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AddExercise extends Activity {

	private int exerciseTypeID = 1;
	private int muscleID = 1;
	private int equipmentID = 1;

	private ArrayList<ExercisesItem> myListExercise;
	private ListExercisesAdapter adapter;
	private ArrayList<FilterItem> listFilterData;
	private ArrayList<ExerciseTypeItem> listTypes;
	private ArrayList<MuscleTargetItem> listMuscles;
	private ArrayList<EquipmentItem> listEquipments;

	private EditText searchExercise;
	private ListView listFullExercises;
	private ListView listFilterExercises;

	// The image we are going to use for the Clear button
	private Drawable clearbutton;
	private Drawable searchbutton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_exercise_library);
		// Set Back button
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		
		listFilterExercises = (ListView) findViewById(R.id.listFilterExercises);

		listFullExercises = (ListView) findViewById(R.id.listFullExercises);
		searchExercise = (EditText) findViewById(R.id.searchExercise);
		clearbutton = getResources().getDrawable(
				R.drawable.text_field_clear_btn);
		searchbutton = getResources().getDrawable(R.drawable.search);
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				searchExercise.clearFocus();
				hideKeyboard(view);
				return false;
			}
		});

		getData();
	}

	public void getData() {
		// Create List Filter
		listFilterData = new ArrayList<FilterItem>();
		listFilterData.add(new FilterItem("Exercise Type", "All Types"));
		listFilterData.add(new FilterItem("Muscle Group", "All Muscles"));
		listFilterData.add(new FilterItem("Equipment", "All Equipments"));

		final ListFilterAdapter filterAdapter = new ListFilterAdapter(
				getApplicationContext(), listFilterData);
		listFilterExercises.setAdapter(filterAdapter);

		final Cursor exerciseType = MainActivity.db.rawQuery(
				"Select * FROM ExerciseType", null);
		final Cursor exerciseMuscle = MainActivity.db.rawQuery(
				"Select * FROM Muscles", null);
		final Cursor exerciseEquipment = MainActivity.db.rawQuery(
				"Select * FROM Equipment", null);
		listTypes = new ArrayList<ExerciseTypeItem>();
		listMuscles = new ArrayList<MuscleTargetItem>();
		listEquipments = new ArrayList<EquipmentItem>();
		while (exerciseType.moveToNext()) {
			listTypes.add(new ExerciseTypeItem(DatabaseUltility
					.GetIntColumnValue(exerciseType,
							DatabaseUltility.ExerciseTypeID), DatabaseUltility
					.GetColumnValue(exerciseType,
							DatabaseUltility.ExerciseTypeName)));
		}
		while (exerciseMuscle.moveToNext()) {
			listMuscles.add(new MuscleTargetItem(DatabaseUltility
					.GetIntColumnValue(exerciseMuscle,
							DatabaseUltility.MuscleID),
					DatabaseUltility.GetColumnValue(exerciseMuscle,
							DatabaseUltility.MuscleName)));
		}
		while (exerciseEquipment.moveToNext()) {
			listEquipments.add(new EquipmentItem(DatabaseUltility
					.GetIntColumnValue(exerciseEquipment,
							DatabaseUltility.EquipmentID), DatabaseUltility
					.GetColumnValue(exerciseEquipment,
							DatabaseUltility.EquipmentName)));
		}

		final ArrayAdapter<ExerciseTypeItem> levelAdapter = new ArrayAdapter<ExerciseTypeItem>(
				getApplicationContext(), android.R.layout.simple_list_item_1, listTypes);
		final ArrayAdapter<MuscleTargetItem> genderAdapter = new ArrayAdapter<MuscleTargetItem>(
				getApplicationContext(), android.R.layout.simple_list_item_1, listMuscles);
		final ArrayAdapter<EquipmentItem> mainGoalAdapter = new ArrayAdapter<EquipmentItem>(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				listEquipments);
		listFilterExercises.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					new AlertDialog.Builder(getApplicationContext())
							.setTitle("Exercise Type")
							.setSingleChoiceItems(levelAdapter, 0,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											dialog.dismiss();
											exerciseTypeID = listTypes
													.get(((AlertDialog) dialog)
															.getListView()
															.getCheckedItemPosition())
													.getExerciseTypeID();
											listFilterData
													.get(0)
													.setValueFilter(
															listTypes
																	.get(((AlertDialog) dialog)
																			.getListView()
																			.getCheckedItemPosition())
																	.toString());
											filterAdapter
													.notifyDataSetChanged();
											adapter.filter(searchExercise
													.getText().toString(),
													exerciseTypeID, muscleID,
													equipmentID);
										}
									}).show();
					break;
				case 1:
					new AlertDialog.Builder(getApplicationContext())
							.setTitle("Muscle Target")
							.setSingleChoiceItems(genderAdapter, 0,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											dialog.dismiss();
											muscleID = listMuscles
													.get(((AlertDialog) dialog)
															.getListView()
															.getCheckedItemPosition())
													.getMuscleID();
											listFilterData
													.get(1)
													.setValueFilter(
															listMuscles
																	.get(((AlertDialog) dialog)
																			.getListView()
																			.getCheckedItemPosition())
																	.toString());
											filterAdapter
													.notifyDataSetChanged();
											adapter.filter(searchExercise
													.getText().toString(),
													exerciseTypeID, muscleID,
													equipmentID);
										}
									}).show();
					break;
				case 2:
					new AlertDialog.Builder(getApplicationContext())
							.setTitle("Equipment")
							.setSingleChoiceItems(mainGoalAdapter, 0,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											dialog.dismiss();
											equipmentID = listEquipments
													.get(((AlertDialog) dialog)
															.getListView()
															.getCheckedItemPosition())
													.getEquipmentID();
											listFilterData
													.get(2)
													.setValueFilter(
															listEquipments
																	.get(((AlertDialog) dialog)
																			.getListView()
																			.getCheckedItemPosition())
																	.toString());
											filterAdapter
													.notifyDataSetChanged();
											adapter.filter(searchExercise
													.getText().toString(),
													exerciseTypeID, muscleID,
													equipmentID);
										}
									}).show();
					break;

				default:
					break;
				}
			}
		});

		// Create List Exercises
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

		adapter = new ListExercisesAdapter(getApplicationContext(), myListExercise);

		listFullExercises.setAdapter(adapter);

		searchExercise.setHint("Type Exercise Name");
		searchExercise.setCompoundDrawablesWithIntrinsicBounds(searchbutton,
				null, null, null);
		searchExercise.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				// handleClearButton();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				adapter.filter(searchExercise.getText().toString(),
						exerciseTypeID, muscleID, equipmentID);
			}
		});

		listFullExercises.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent exerciseDetailIntent = new Intent(getApplicationContext(),
						ExerciseDetail.class);
				exerciseDetailIntent.putExtra("ExerciseID",
						myListExercise.get(position).getExerciseID());
				// exerciseDetailIntent.putExtra("workoutID", workoutID);
				// searchExercise.clearFocus();
				startActivity(exerciseDetailIntent);
			}
		});
		listFullExercises.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				return true;
			}
		});

	}

	// An ban phim khi bam ra ban ngoai
	protected void hideKeyboard(View view) {
		InputMethodManager in = (InputMethodManager) getApplicationContext()
				.getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
