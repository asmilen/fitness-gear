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
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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
import android.widget.TextView;
import android.widget.Toast;

public class AddExercise extends Activity {

	private static String workoutID;
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
	// private Drawable clearbutton;
	private Drawable searchbutton;

	private EditText txtSets;
	private EditText txtRest;
	private EditText txtRepsMin;
	private EditText txtRepsMax;
	private EditText txtKg;
	private EditText txtInterval;

	private TextView tvRepsMin;
	private TextView tvRepsMax;
	private TextView tvKg;
	private TextView tvInterval;

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

		Bundle bund = getIntent().getExtras();
		workoutID = bund.getString("WorkoutID");

		listFilterExercises = (ListView) findViewById(R.id.listFilterExercises);

		listFullExercises = (ListView) findViewById(R.id.listFullExercises);
		searchExercise = (EditText) findViewById(R.id.searchExercise);
		// clearbutton = getResources().getDrawable(
		// R.drawable.text_field_clear_btn);
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
				AddExercise.this, listFilterData);
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
				AddExercise.this, android.R.layout.simple_list_item_1,
				listTypes);
		final ArrayAdapter<MuscleTargetItem> genderAdapter = new ArrayAdapter<MuscleTargetItem>(
				AddExercise.this, android.R.layout.simple_list_item_1,
				listMuscles);
		final ArrayAdapter<EquipmentItem> mainGoalAdapter = new ArrayAdapter<EquipmentItem>(
				AddExercise.this, android.R.layout.simple_list_item_1,
				listEquipments);
		listFilterExercises.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					new AlertDialog.Builder(AddExercise.this)
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
					new AlertDialog.Builder(AddExercise.this)
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
					new AlertDialog.Builder(AddExercise.this)
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

		adapter = new ListExercisesAdapter(AddExercise.this, myListExercise);

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
				Intent exerciseDetailIntent = new Intent(AddExercise.this,
						ExerciseDetail.class);
				exerciseDetailIntent.putExtra("ExerciseID",
						myListExercise.get(position).getExerciseID());
				// exerciseDetailIntent.putExtra("workoutID", workoutID);
				// searchExercise.clearFocus();
				startActivity(exerciseDetailIntent);
			}
		});
		listFullExercises
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						final int pos = position;
						// Get the layout inflater
						LayoutInflater inflater = AddExercise.this
								.getLayoutInflater();
						// Get View from inflater
						View addExerciseView = inflater.inflate(
								R.layout.dialog_add_exercise, null);

						txtSets = (EditText) addExerciseView
								.findViewById(R.id.txtSets);
						txtRest = (EditText) addExerciseView
								.findViewById(R.id.txtRest);
						txtRepsMin = (EditText) addExerciseView
								.findViewById(R.id.txtRepsMin);
						txtRepsMax = (EditText) addExerciseView
								.findViewById(R.id.txtRepsMax);
						txtKg = (EditText) addExerciseView
								.findViewById(R.id.txtKg);
						txtInterval = (EditText) addExerciseView
								.findViewById(R.id.txtInterval);

						tvRepsMin = (TextView) addExerciseView
								.findViewById(R.id.tvRepsMin);
						tvRepsMax = (TextView) addExerciseView
								.findViewById(R.id.tvRepsMax);
						tvKg = (TextView) addExerciseView
								.findViewById(R.id.tvKg);
						tvInterval = (TextView) addExerciseView
								.findViewById(R.id.tvInterval);

						if (myListExercise.get(position).getEquipment() == 2
								|| myListExercise.get(position).getEquipment() == 3
								|| myListExercise.get(position).getEquipment() == 6
								|| myListExercise.get(position).getEquipment() == 9
								|| myListExercise.get(position).getEquipment() == 10
								|| myListExercise.get(position).getEquipment() == 11
								|| myListExercise.get(position).getEquipment() == 12) {
							tvRepsMin.setVisibility(View.VISIBLE);
							txtRepsMin.setVisibility(View.VISIBLE);
							tvRepsMax.setVisibility(View.VISIBLE);
							txtRepsMax.setVisibility(View.VISIBLE);
							tvKg.setVisibility(View.VISIBLE);
							txtKg.setVisibility(View.VISIBLE);
							tvInterval.setVisibility(View.GONE);
							txtInterval.setVisibility(View.GONE);
						} else {
							tvRepsMin.setVisibility(View.VISIBLE);
							txtRepsMin.setVisibility(View.VISIBLE);
							tvRepsMax.setVisibility(View.VISIBLE);
							txtRepsMax.setVisibility(View.VISIBLE);
							tvKg.setVisibility(View.GONE);
							txtKg.setVisibility(View.GONE);
							tvInterval.setVisibility(View.GONE);
							txtInterval.setVisibility(View.GONE);
						}
						if (myListExercise.get(position).getExerciseType() == 2) {
							tvRepsMin.setVisibility(View.GONE);
							txtRepsMin.setVisibility(View.GONE);
							tvRepsMax.setVisibility(View.GONE);
							txtRepsMax.setVisibility(View.GONE);
							tvKg.setVisibility(View.GONE);
							txtKg.setVisibility(View.GONE);
							tvInterval.setVisibility(View.VISIBLE);
							txtInterval.setVisibility(View.VISIBLE);
						}
						AlertDialog.Builder builder = new AlertDialog.Builder(
								AddExercise.this);
						builder.setView(addExerciseView)
								.setTitle("Add Exercise")
								.setPositiveButton("Add Exercise",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												try {
													if (txtSets.getText()
															.toString()
															.equals("")
															|| txtSets
																	.getText()
																	.toString() == null) {
														txtSets.setText("0");
													}
													if (txtSets.getText()
															.toString()
															.equals("")
															|| txtSets
																	.getText()
																	.toString() == null) {
														txtRest.setText("0");
													}
													if (txtRepsMin.getText()
															.toString()
															.equals("")
															|| txtRepsMin
																	.getText()
																	.toString() == null) {
														txtRepsMin.setText("0");
													}
													if (txtRepsMax.getText()
															.toString()
															.equals("")
															|| txtRepsMax
																	.getText()
																	.toString() == null) {
														txtRepsMax.setText("0");
													}
													if (txtKg.getText()
															.toString()
															.equals("")
															|| txtKg.getText()
																	.toString() == null) {
														txtKg.setText("0");
													}
													if (txtInterval.getText()
															.toString()
															.equals("")
															|| txtInterval
																	.getText()
																	.toString() == null) {
														txtInterval
																.setText("0");
													}
													int sets = Integer
															.parseInt(txtSets
																	.getText()
																	.toString());
													int rests = Integer
															.parseInt(txtRest
																	.getText()
																	.toString());
													int repsMin = Integer
															.parseInt(txtRepsMin
																	.getText()
																	.toString());
													int repsMax = Integer
															.parseInt(txtRepsMax
																	.getText()
																	.toString());
													int kg = Integer
															.parseInt(txtKg
																	.getText()
																	.toString());
													int interval = Integer
															.parseInt(txtInterval
																	.getText()
																	.toString());
													if (sets > 0 && sets <= 10
															&& rests > 0 && rests <=300
															&& repsMin >= 0 && repsMin <= 100
															&& repsMax >= repsMin && repsMax <= 100
															&& kg >= 0 && kg < 200
															&& interval >= 0 && interval <= 3600) {
														ContentValues contentWorkoutExercise = new ContentValues();
														contentWorkoutExercise
																.put("WorkoutID",
																		workoutID);
														contentWorkoutExercise
																.put("ExerciseID",
																		myListExercise
																				.get(pos)
																				.getExerciseID());

														contentWorkoutExercise
																.put("Sets",
																		txtSets.getText()
																				.toString());
														contentWorkoutExercise
																.put("Rests",
																		txtRest.getText()
																				.toString());
														contentWorkoutExercise
																.put("RepsMin",
																		txtRepsMin
																				.getText()
																				.toString());
														contentWorkoutExercise
																.put("RepsMax",
																		txtRepsMax
																				.getText()
																				.toString());
														contentWorkoutExercise
																.put("Kg",
																		txtKg.getText()
																				.toString());
														contentWorkoutExercise
																.put("Interval",
																		txtInterval
																				.getText()
																				.toString());
														int addExercise = (int) MainActivity.db
																.insert("Workout_Exercise",
																		null,
																		contentWorkoutExercise);
														if (addExercise > 0) {
															Toast.makeText(
																	AddExercise.this,
																	"Add Successfull with WorkoutID "
																			+ workoutID
																			+ " and exerciseID "
																			+ myListExercise
																					.get(pos)
																					.getExerciseID(),
																	Toast.LENGTH_LONG)
																	.show();
														}
													}
												} catch (Exception ex) {
													Toast.makeText(
															AddExercise.this,
															"" + ex,
															Toast.LENGTH_LONG)
															.show();
												}
											}
										})
								.setNegativeButton("Cancel",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										});
						builder.show();
						return true;
					}
				});

	}

	// Calculate Total Exercise and Total Sets in the workout
	public static void updateWorkoutAfterCalculate() {
		Cursor totalExerciseCursor = MainActivity.db
				.rawQuery(
						"SELECT count(WorkoutID) as TotalExercises FROM Workout_Exercise WHERE WorkoutID = "
								+ workoutID, null);
		int totalExercise = 0;
		int totalSets = 0;
		while (totalExerciseCursor.moveToNext()) {
			totalExercise = DatabaseUltility.GetIntColumnValue(
					totalExerciseCursor, DatabaseUltility.TotalExercises);
		}
		Cursor totalSetsCursor = MainActivity.db.rawQuery(
				"SELECT sum(Sets) as TotalSets FROM Workout_Exercise WHERE WorkoutID = "
						+ workoutID, null);
		while (totalSetsCursor.moveToNext()) {
			totalSets = DatabaseUltility.GetIntColumnValue(totalSetsCursor,
					DatabaseUltility.TotalSets);
		}
		ContentValues contentWorkout = new ContentValues();
		contentWorkout.put("TotalExercises", totalExercise);
		contentWorkout.put("TotalSets", totalSets);
		int updateWorkout = MainActivity.db.update("Workout", contentWorkout,
				"WorkoutID = ?", new String[] { workoutID });
		if (updateWorkout > 0) {
			// Toast.makeText(), "Update Successfull",
			// Toast.LENGTH_LONG).show();
		}
	}

	// An ban phim khi bam ra ban ngoai
	protected void hideKeyboard(View view) {
		InputMethodManager in = (InputMethodManager) AddExercise.this
				.getSystemService(AddExercise.this.INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		updateWorkoutAfterCalculate();
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == android.R.id.home) {
			updateWorkoutAfterCalculate();
			this.finish();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
