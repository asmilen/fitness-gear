package com.fitnessgear;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.TimeZone;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessgear.adapter.HomeViewPagerAdapter;
import com.fitnessgear.database.DatabaseUltility;

public class Home extends Fragment {

	private ViewPager pager;
	private HomeViewPagerAdapter adapter;
	private PagerTabStrip pagerTab;
	private LinearLayout homeLayout;

	private ImageView avaImage;
	private static TextView tvUserName;
	private static TextView tvAge;
	private static TextView tvGender;
	private static TextView tvWeight;
	private static TextView tvHeight;
	private static TextView tvBodyFat;
	private static TextView tvBMI;

	private EditText etUserName;
	private EditText etDoB;
	private DatePickerDialog datePicker;

	public Home() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		setHasOptionsMenu(true);

		// Hide keyboard when click layout
		homeLayout = (LinearLayout) rootView.findViewById(R.id.homeLayout);
		homeLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				hideKeyboard(view);
				UserInformation.getData();
				return false;
			}
		});
		try {
			pager = (ViewPager) rootView.findViewById(R.id.viewPager);
			pagerTab = (PagerTabStrip) rootView
					.findViewById(R.id.pagerTabStrip);
			adapter = new HomeViewPagerAdapter(getChildFragmentManager());
			pagerTab.setTabIndicatorColor(Color.BLUE);
			pagerTab.setBackgroundColor(Color.CYAN);
			pagerTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
			pager.setAdapter(adapter);
			pagerTab.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					UserInformation.getData();
					return false;
				}
			});
		} catch (Exception ex) {
			Toast.makeText(getActivity(), "" + ex, Toast.LENGTH_LONG).show();
		}

		avaImage = (ImageView) rootView.findViewById(R.id.avaImage);
		tvUserName = (TextView) rootView.findViewById(R.id.tvUserName);
		tvAge = (TextView) rootView.findViewById(R.id.tvAge);
		tvGender = (TextView) rootView.findViewById(R.id.tvGender);
		tvWeight = (TextView) rootView.findViewById(R.id.tvWeight);
		tvHeight = (TextView) rootView.findViewById(R.id.tvHeight);
		tvBodyFat = (TextView) rootView.findViewById(R.id.tvBodyFat);
		tvBMI = (TextView) rootView.findViewById(R.id.tvBMI);
		getData();

		return rootView;
	}

	// <<<<<<< .mine
	//
	// public static void getData() {
	// Cursor user = MainActivity.db.rawQuery(
	// "SELECT * FROM User Where UserID = 1", null);
	// while (user.moveToNext()) {
	// tvUserName.setText(DatabaseUltility.GetColumnValue(user,
	// DatabaseUltility.UserName));
	// // tvAge.setText("Age: " + DatabaseUltility.GetColumnValue(user,
	// // DatabaseUltility.DateOfBirth));
	// tvGender.setText("Gender: "
	// + DatabaseUltility.GetColumnValue(user,
	// DatabaseUltility.Gender));
	// tvWeight.setText("Weight: "
	// + DatabaseUltility.GetFloatColumnValue(user,
	// DatabaseUltility.Weight));
	// tvHeight.setText("Height: "
	// + DatabaseUltility.GetFloatColumnValue(user,
	// DatabaseUltility.Height));
	// tvBodyFat.setText("Body Fat: "
	// + DatabaseUltility.GetFloatColumnValue(user,
	// DatabaseUltility.BodyFat));
	// =======

	public static void getData() {
		Cursor user = MainActivity.db.rawQuery(
				"SELECT * FROM User Where UserID = 1", null);
		while (user.moveToNext()) {
			tvUserName.setText(DatabaseUltility.GetColumnValue(user,
					DatabaseUltility.UserName));
			String Year = DatabaseUltility.GetColumnValue(user,
					DatabaseUltility.DateOfBirth).substring(6);
			int age = Calendar.getInstance().get(Calendar.YEAR)
					- Integer.valueOf(Year);
			tvAge.setText("Age: " + age);
			tvGender.setText("Gender: "
					+ DatabaseUltility.GetColumnValue(user,
							DatabaseUltility.Gender));
			tvWeight.setText("Weight: "
					+ DatabaseUltility.GetFloatColumnValue(user,
							DatabaseUltility.Weight));
			tvHeight.setText("Height: "
					+ DatabaseUltility.GetFloatColumnValue(user,
							DatabaseUltility.Height));
			tvBodyFat.setText("Body Fat: "
					+ DatabaseUltility.GetFloatColumnValue(user,
							DatabaseUltility.BodyFat));
			float BMI = 0;
			if (DatabaseUltility.GetFloatColumnValue(user,
					DatabaseUltility.Weight) != 0
					&& DatabaseUltility.GetFloatColumnValue(user,
							DatabaseUltility.Height) != 0) {
				float weight = DatabaseUltility.GetFloatColumnValue(user,
						DatabaseUltility.Weight);
				float height = DatabaseUltility.GetFloatColumnValue(user,
						DatabaseUltility.Height) / 100;
				BMI = weight / (height * height);
			}
			DecimalFormat form = new DecimalFormat("0.00");
			tvBMI.setText("BMI: " + form.format(BMI));
		}
	}

	// An ban phim khi bam ra ben ngoai
	protected void hideKeyboard(View view) {
		InputMethodManager in = (InputMethodManager) getActivity()
				.getSystemService(getActivity().INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.edit_user, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.editUser) {

			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();
			// Get View from inflater
			View editUserView = inflater.inflate(R.layout.edit_user, null);

			etUserName = (EditText) editUserView.findViewById(R.id.etUserName);
			etDoB = (EditText) editUserView.findViewById(R.id.etDoB);

			try {
				// Set text to Edit text after select Date
				Cursor userCursor = MainActivity.db.rawQuery(
						"Select * From User Where UserID = 1", null);

				while (userCursor.moveToNext()) {
					etUserName.setText(DatabaseUltility.GetColumnValue(
							userCursor, DatabaseUltility.UserName));
					etDoB.setText(DatabaseUltility.GetColumnValue(userCursor,
							DatabaseUltility.DateOfBirth));
				}
				DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

					// when dialog box is closed, below method will be called.
					public void onDateSet(DatePicker view, int selectedYear,
							int selectedMonth, int selectedDay) {
						int year;
						int month;
						int day;
						year = Integer.valueOf(selectedYear);
						month = Integer.valueOf(selectedMonth + 1);
						day = Integer.valueOf(selectedDay);
						if (day < 10) {
							etDoB.setText("0" + day + "/" + month + "/" + year);
						}
						if (month < 10) {
							etDoB.setText(day + "/" + "0" + month + "/" + year);
						}
						if (day < 10 && month < 10) {
							etDoB.setText("0" + day + "/" + "0" + month + "/"
									+ year);
						}

						if (day >= 10 && month >= 10) {
							etDoB.setText(day + "/" + month + "/" + year);
						}
						etDoB.clearFocus();
						etUserName.clearFocus();
					}
				};
				// Get current Date
				Calendar cal = Calendar.getInstance(TimeZone.getDefault());

				// Delete last AND

				// Create Date Picker Dialog

				if (etDoB.getText().toString() != "") {
					String[] dob = etDoB.getText().toString().split("/");
					datePicker = new DatePickerDialog(getActivity(),
							datePickerListener, Integer.valueOf(dob[2]),
							Integer.valueOf(dob[1]) - 1,
							Integer.valueOf(dob[0]));
				} else {
					datePicker = new DatePickerDialog(getActivity(),
							datePickerListener, cal.get(Calendar.YEAR),
							cal.get(Calendar.MONTH),
							cal.get(Calendar.DAY_OF_MONTH));
				}

				datePicker.setTitle("Select The Date");
				// Show Date Picker Dialog When edit text Focus
				etDoB.setOnFocusChangeListener(new OnFocusChangeListener() {
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// TODO Auto-generated method stub
						if (hasFocus) {
							datePicker.show();
						}
					}
				});

				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle("Edit User")
						.setView(editUserView)
						.setPositiveButton("Update User",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										MainActivity.db = MainActivity.dbHelper
												.getWritableDatabase();
										String year = etDoB.getText()
												.toString().substring(6);
										int age = Calendar.getInstance().get(
												Calendar.YEAR)
												- Integer.valueOf(year);
										if (!etUserName.getText().toString()
												.equals("")
												&& !etDoB.getText().toString()
														.equals("") && age > 0) {
											ContentValues contentUser = new ContentValues();
											contentUser.put("UserName",
													etUserName.getText()
															.toString());
											contentUser.put("DateOfBirth",
													etDoB.getText().toString());
											int updateUser = MainActivity.db
													.update("User",
															contentUser,
															"UserID = 1", null);
											if (updateUser > 0) {
												getData();
											}
										}
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								});
				builder.show();
			} catch (Exception e) {
				Toast.makeText(getActivity(), e + "", Toast.LENGTH_LONG).show();
			}
		}

		return super.onOptionsItemSelected(item);
	}
}
