package com.fitnessgear.child;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.FitnessLevelItem;
import com.fitnessgear.model.GenderItem;
import com.fitnessgear.model.MainGoalItem;

public class AddPlan extends Activity {

	private ScrollView createPlanLayout;

	private DatePickerDialog datePicker;
	private EditText planName;
	private Spinner mainGoal;
	private Spinner gender;
	private Spinner fitnessLevel;
	private EditText createdBy;
	private EditText dateCreated;
	private EditText totalWeek;
	private EditText aveDay;
	private EditText aveWorkoutTime;
	private EditText totalTimeAWeek;
	private EditText totalCardioTime;

	private Button btnCancel;
	private Button btnCreatePlan;

	private ArrayList<MainGoalItem> mainGoalItem;
	private ArrayList<GenderItem> genderItem;
	private ArrayList<FitnessLevelItem> fitnessLevelItem;

	private int mainGoalID;
	private int genderID;
	private int fitnessLevelID;
	private int planID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_plan);
		ActionBar actionBar = getActionBar();
		// Set back button on action Bar
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		// Get last planID from sqlite
		Cursor planIDCursor = MainActivity.db.rawQuery("Select * from Plan",
				null);

		while (planIDCursor.moveToNext()) {
			planID = DatabaseUltility.GetIntColumnValue(planIDCursor,
					DatabaseUltility.PlanID);
		}
		planID++;

		createPlanLayout = (ScrollView) findViewById(R.id.createPlanLayout);
		createPlanLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				hideKeyboard(v);

				return false;
			}
		});

		planName = (EditText) findViewById(R.id.txtPlanName1);
		mainGoal = (Spinner) findViewById(R.id.spMainGoal);
		gender = (Spinner) findViewById(R.id.spGender);
		fitnessLevel = (Spinner) findViewById(R.id.spFitnessLevel);
		createdBy = (EditText) findViewById(R.id.txtCreatedBy);
		dateCreated = (EditText) findViewById(R.id.txtDateCreated);
		totalWeek = (EditText) findViewById(R.id.txtTotalWeek);
		aveDay = (EditText) findViewById(R.id.txtDayPerWeek);
		aveWorkoutTime = (EditText) findViewById(R.id.txtWorkoutTime);
		totalTimeAWeek = (EditText) findViewById(R.id.txtTimePerWeek);
		totalCardioTime = (EditText) findViewById(R.id.txtTotalCardioTime);

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCreatePlan = (Button) findViewById(R.id.btnCreatePlan);
		btnCreatePlan.setEnabled(false);
		btnCreatePlan
				.setTextColor(getResources().getColor(R.color.button_text));

		// Add Data to ArrayList for Spinner
		mainGoalItem = new ArrayList<MainGoalItem>();
		Cursor mainGoalCursor = MainActivity.db.rawQuery(
				"Select * From Main_Goal", null);
		while (mainGoalCursor.moveToNext()) {
			mainGoalItem.add(new MainGoalItem(DatabaseUltility
					.GetIntColumnValue(mainGoalCursor,
							DatabaseUltility.MainGoalID), DatabaseUltility
					.GetColumnValue(mainGoalCursor,
							DatabaseUltility.MainGoalName)));
		}

		genderItem = new ArrayList<GenderItem>();
		Cursor genderCursor = MainActivity.db.rawQuery("Select * From Gender",
				null);
		while (genderCursor.moveToNext()) {
			genderItem
					.add(new GenderItem(DatabaseUltility.GetIntColumnValue(
							genderCursor, DatabaseUltility.GenderID),
							DatabaseUltility.GetColumnValue(genderCursor,
									DatabaseUltility.GenderName)));
		}

		fitnessLevelItem = new ArrayList<FitnessLevelItem>();
		Cursor fitnessLevelCursor = MainActivity.db.rawQuery(
				"Select * From FitnessLevel", null);
		while (fitnessLevelCursor.moveToNext()) {
			fitnessLevelItem.add(new FitnessLevelItem(DatabaseUltility
					.GetIntColumnValue(fitnessLevelCursor,
							DatabaseUltility.FitnessLevelID), DatabaseUltility
					.GetColumnValue(fitnessLevelCursor,
							DatabaseUltility.FitnessLevelName)));
		}

		ArrayAdapter<MainGoalItem> mainGoalAdapter = new ArrayAdapter<MainGoalItem>(
				this, android.R.layout.simple_spinner_item, mainGoalItem);
		ArrayAdapter<GenderItem> genderAdapter = new ArrayAdapter<GenderItem>(
				this, android.R.layout.simple_spinner_item, genderItem);
		ArrayAdapter<FitnessLevelItem> fitnessLevelAdapter = new ArrayAdapter<FitnessLevelItem>(
				this, android.R.layout.simple_spinner_item, fitnessLevelItem);
		mainGoal.setAdapter(mainGoalAdapter);
		gender.setAdapter(genderAdapter);
		fitnessLevel.setAdapter(fitnessLevelAdapter);

		mainGoal.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				MainGoalItem item = (MainGoalItem) mainGoal.getSelectedItem();
				mainGoalID = item.getMainGoalID();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		gender.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				GenderItem item = (GenderItem) gender.getSelectedItem();
				genderID = item.getGenderID();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		fitnessLevel.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				FitnessLevelItem item = (FitnessLevelItem) fitnessLevel
						.getSelectedItem();
				fitnessLevelID = item.getFitnessLevelID();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// Listener text change of edittext
		planName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				if (!planName.getText().toString().equals("")
						&& !totalWeek.getText().toString().equals("")
						&& !aveDay.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(true);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.text_title_color));
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (planName.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(false);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.button_text));
				}

			}
		});
		totalWeek.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!planName.getText().toString().equals("")
						&& !totalWeek.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(true);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.text_title_color));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (totalWeek.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(false);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.button_text));
				}

			}
		});

		aveDay.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!planName.getText().toString().equals("")
						&& !totalWeek.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(true);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.text_title_color));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (aveDay.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(false);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.button_text));
				}

			}
		});

		aveWorkoutTime.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!planName.getText().toString().equals("")
						&& !totalWeek.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(true);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.text_title_color));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (aveWorkoutTime.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(false);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.button_text));
				}

			}
		});

		totalTimeAWeek.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!planName.getText().toString().equals("")
						&& !totalWeek.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(true);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.text_title_color));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (totalTimeAWeek.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(false);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.button_text));
				}

			}
		});

		totalCardioTime.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!planName.getText().toString().equals("")
						&& !totalWeek.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(true);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.text_title_color));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (totalCardioTime.getText().toString().equals("")) {
					btnCreatePlan.setEnabled(false);
					btnCreatePlan.setTextColor(getResources().getColor(
							R.color.button_text));
				}

			}
		});

		try {
			// Set text to Edit text after select Date
			DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

				// when dialog box is closed, below method will be called.
				public void onDateSet(DatePicker view, int selectedYear,
						int selectedMonth, int selectedDay) {
					int year = Integer.valueOf(selectedYear);
					int month = Integer.valueOf(selectedMonth + 1);
					int day = Integer.valueOf(selectedDay);
					if (day < 10) {
						dateCreated.setText("0" + day + "/" + month + "/"
								+ year);
					}
					if (month < 10) {
						dateCreated.setText(day + "/" + "0" + month + "/"
								+ year);
					}
					if (day < 10 && month < 10) {
						dateCreated.setText("0" + day + "/" + "0" + month + "/"
								+ year);
					}
					if (day >= 10 && month >= 10) {
						dateCreated.setText(day + "/" + month + "/" + year);
					}
				}
			};
			// Get current Date
			Calendar cal = Calendar.getInstance(TimeZone.getDefault());
			// Create Date Picker Dialog
			datePicker = new DatePickerDialog(this, datePickerListener,
					cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					cal.get(Calendar.DAY_OF_MONTH));
			datePicker.setTitle("Select The Date");
			// Show Date Picker Dialog When edit text Focus
			dateCreated.setOnFocusChangeListener(new OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if (hasFocus) {
						datePicker.show();
					}
				}
			});

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
		}

		// Set Activity finish when click Cancel button

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddPlan.this.finish();
			}
		});
		// Set Insert Plan to Sqlite when click
		btnCreatePlan.setOnClickListener(new OnClickListener() {

			private String ErrorMessage;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String textPlanName = planName.getText().toString();
				int numTotalWeek = Integer.parseInt(totalWeek.getText()
						.toString());
				float numAveDay = 0, numAveWorkoutTime = 0;
				int numTotalTimeAWeek = 0, numTotalCardioTime = 0;
				try {
					numAveDay = Float.parseFloat(aveDay.getText().toString());
				} catch (Exception ex) {
				}
				try {
					numAveWorkoutTime = Float.parseFloat(aveWorkoutTime
							.getText().toString());
				} catch (Exception ex) {
				}
				try {
					numTotalTimeAWeek = Integer.parseInt(totalTimeAWeek
							.getText().toString());
				} catch (Exception ex) {
				}
				try {
					numTotalCardioTime = Integer.parseInt(totalCardioTime
							.getText().toString());
				} catch (Exception ex) {
				}

				if (DataValidated(textPlanName.length(), numTotalWeek,
						numAveDay, numAveWorkoutTime, numTotalTimeAWeek,
						numTotalCardioTime)) {
					final EditText numberWorkout = new EditText(AddPlan.this);
					numberWorkout.setInputType(InputType.TYPE_CLASS_NUMBER);
					InputFilter[] fArray = new InputFilter[1];
					fArray[0] = new InputFilter.LengthFilter(3);
					numberWorkout.setFilters(fArray);
					new AlertDialog.Builder(AddPlan.this)
							.setTitle("CreateWorkout")
							.setMessage("Number Of Workout To Create")

							.setView(numberWorkout)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											if (!numberWorkout.getText()
													.toString().equals("")) {
												int numOfWorkout = Integer
														.parseInt(numberWorkout
																.getText()
																.toString());
												int insertPlan = 0;
												ContentValues contentPlan = new ContentValues();
												// contentPlan.put("PlanID",
												// planID);
												contentPlan.put("PlanName",
														planName.getText()
																.toString());
												contentPlan.put("MainGoal",
														mainGoalID);
												contentPlan.put("Gender",
														genderID);
												contentPlan.put("FitnessLevel",
														fitnessLevelID);
												contentPlan.put("CreatedBy",
														createdBy.getText()
																.toString());
												contentPlan.put("DateCreated",
														dateCreated.getText()
																.toString());
												contentPlan
														.put("TotalWeeks",
																Integer.parseInt(totalWeek
																		.getText()
																		.toString()));
												String aveDay1 = aveDay
														.getText().toString();
												if (aveDay1 != null
														&& !aveDay1.equals("")) {
													contentPlan
															.put("AveDay",
																	Float.parseFloat(aveDay1));
												} else
													contentPlan
															.put("AveDay", 0);

												String aveWorkoutTime1 = aveWorkoutTime
														.getText().toString();
												if (aveWorkoutTime1 != null
														&& !aveWorkoutTime1
																.equals("")) {
													contentPlan
															.put("AveWorkoutTime",
																	Float.parseFloat(aveWorkoutTime1));
												}
												else
													contentPlan
															.put("AveWorkoutTime", 0);
												String TotaltimeAWeek1 = totalTimeAWeek
														.getText().toString();
												if (TotaltimeAWeek1 != null
														&& !TotaltimeAWeek1
																.equals("")) {
													contentPlan
															.put("TotalTimeAWeek",
																	Integer.parseInt(TotaltimeAWeek1));
												}
												else
													contentPlan
															.put("TotalTimeAWeek", 0);
												String cardioTime = totalCardioTime
														.getText().toString();
												if (cardioTime != null
														&& !cardioTime
																.equals("")) {
													contentPlan
															.put("TotalCardioTime",
																	Integer.parseInt(cardioTime));
												}
												else
													contentPlan
															.put("TotalCardioTime2", 0);
												int maxWorkout = Integer
														.parseInt(totalWeek
																.getText()
																.toString()) * 7;
												int minWorkout = (Integer
														.parseInt(totalWeek
																.getText()
																.toString()) - 1) * 7;
												if (numOfWorkout > minWorkout
														&& numOfWorkout <= maxWorkout) {
													insertPlan = (int) MainActivity.db
															.insert("Plan",
																	null,
																	contentPlan);
												} else {
													Toast.makeText(
															AddPlan.this,
															"Number of Workout must be greater than "
																	+ minWorkout
																	+ " and less than "
																	+ maxWorkout,
															Toast.LENGTH_SHORT)
															.show();
												}

												if (insertPlan > 0) {

													Intent intent = new Intent(
															AddPlan.this,
															CreateWorkout.class);
													intent.putExtra("PlanID",
															planID);
													intent.putExtra(
															"NumOfWorkout",
															numOfWorkout);
													startActivity(intent);
												}
											}
										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											// dialog.dismiss();
										}
									}).show();
				} else {
					Toast.makeText(getApplicationContext(), ErrorMessage,
							Toast.LENGTH_LONG).show();
				}
			}

			private boolean DataValidated(int length, int numTotalWeek,
					float numAveDay, float numAveWorkoutTime,
					int numTotalTimeAWeek, int numTotalCardioTime) {
				// TODO Auto-generated method stub
				ErrorMessage = "";

				if (!(length > 4 && length < 50))
					ErrorMessage += "Plan name must be greater than 4 and less than 50 characters \n";

				if (!(numTotalWeek < 99 && numTotalWeek >= 1))
					ErrorMessage += "Total week must be greater than 1 week and less than 99 weeks \n";

				if (numAveDay != 0)
					if (!(numAveDay <= 7 && numAveDay >= 1))
						ErrorMessage += "Average day must between 1 and 7 days \n";
				if (numAveWorkoutTime != 0)
					if (!(numAveWorkoutTime < 99 && numAveWorkoutTime > 1))
						ErrorMessage += "Average workout time must be greater than 1 minute and less than 99.9 minutes \n";

				if (numTotalTimeAWeek != 0)
					if (!(numTotalTimeAWeek < 999 && numTotalTimeAWeek > 1))
						ErrorMessage += "Total time train per week must be greater than 1 minute and less than 999 minutes \n";

				if (numTotalCardioTime != 0)
					if (!(numTotalCardioTime < 99 && numTotalCardioTime > 1))
						ErrorMessage += "Total cardio time must be greater than 1 minute and less than 999 minutes \n";

				if (ErrorMessage.equals(""))
					return true;
				return false;
			}
		});
	}

	// Hide Keyboard when click outside
	protected void hideKeyboard(View view) {
		InputMethodManager in = (InputMethodManager) getApplicationContext()
				.getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		showWarning();
		// super.onBackPressed();
	}

	public void showWarning() {
		new AlertDialog.Builder(AddPlan.this)
				.setTitle("Warning")
				.setMessage(
						"Create plan not complete\n"
								+ "If you back to list plan, data will be lost\n"
								+ "Are you sure?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// MainActivity.db =
						// MainActivity.dbHelper.getWritableDatabase();
						// MainActivity.db.execSQL("DELTE FROM Plan WHERE PlanID = "
						// + --planID);
						// Toast.makeText(AddPlan.this, "DELETE SUCCESSFULL",
						// Toast.LENGTH_SHORT).show();
						AddPlan.this.finish();
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						}).show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			showWarning();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
