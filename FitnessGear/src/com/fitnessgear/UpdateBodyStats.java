package com.fitnessgear;

import com.fitnessgear.database.DatabaseUltility;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Toast;

public class UpdateBodyStats extends Fragment {

	private static Button btnSaveUpdateStats;
	private static ScrollView updateStatsLayout;

	private static EditText etWeight;
	private static EditText etBodyFat;
	private static EditText etHeight;
	private static EditText etChest;
	private static EditText etWaist;
	private static EditText etArms;
	private static EditText etShoulders;
	private static EditText etForearms;
	private static EditText etNeck;
	private static EditText etHips;
	private static EditText etThighs;
	private static EditText etCalves;

	private String UserID = "1";
	
	private static int buttonDisabledText;
	private static int buttonEnabledText;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View rootView = inflater.inflate(R.layout.fragment_update_stats,
				container, false);

		updateStatsLayout = (ScrollView) rootView
				.findViewById(R.id.updateStatsLayout);

		updateStatsLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				UserInformation.getData();
				return false;
			}
		});

		btnSaveUpdateStats = (Button) rootView
				.findViewById(R.id.btnSaveUpdateStats);

		etWeight = (EditText) rootView.findViewById(R.id.etWeightBody);
		etBodyFat = (EditText) rootView.findViewById(R.id.etBodyFat);
		etHeight = (EditText) rootView.findViewById(R.id.etHeightBody);
		etChest = (EditText) rootView.findViewById(R.id.etChestBody);
		etWaist = (EditText) rootView.findViewById(R.id.etWaistBody);
		etArms = (EditText) rootView.findViewById(R.id.etArmsBody);
		etShoulders = (EditText) rootView.findViewById(R.id.etShouldersBody);
		etForearms = (EditText) rootView.findViewById(R.id.etForearmsBody);
		etNeck = (EditText) rootView.findViewById(R.id.etNeckBody);
		etHips = (EditText) rootView.findViewById(R.id.etHipsBody);
		etThighs = (EditText) rootView.findViewById(R.id.etThighsBody);
		etCalves = (EditText) rootView.findViewById(R.id.etCalvesBody);
		
		buttonDisabledText = getResources().getColor(R.color.button_text);
		buttonEnabledText = getResources().getColor(R.color.text_title_color);
		
		btnSaveUpdateStats.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {

					float weight = Float.parseFloat(etWeight.getText()
							.toString());
					float bodyFat = Float.parseFloat(etBodyFat.getText()
							.toString());
					float height = Float.parseFloat(etHeight.getText()
							.toString());
					float chest = Float
							.parseFloat(etChest.getText().toString());
					float waist = Float
							.parseFloat(etWaist.getText().toString());
					float arms = Float.parseFloat(etArms.getText().toString());
					float shoulders = Float.parseFloat(etShoulders.getText()
							.toString());
					float forearms = Float.parseFloat(etForearms.getText()
							.toString());
					float neck = Float.parseFloat(etNeck.getText().toString());
					float hips = Float.parseFloat(etHips.getText().toString());
					float thighs = Float.parseFloat(etThighs.getText()
							.toString());
					float calves = Float.parseFloat(etCalves.getText()
							.toString());
					if (weight >= 0 && weight <= 200 && bodyFat >= 0
							&& bodyFat <= 100 && height >= 0 && height <= 300
							&& chest >= 0 && chest <= 200 && waist >= 0
							&& waist <= 200 && arms >= 0 && arms <= 200
							&& shoulders >= 0 && shoulders <= 200
							&& forearms >= 0 && forearms <= 200 && neck >= 0
							&& neck <= 200 && hips >= 0 && hips <= 200
							&& thighs >= 0 && thighs <= 200 && calves >= 0
							&& calves <= 200) {

						MainActivity.db = MainActivity.dbHelper
								.getWritableDatabase();
						ContentValues userContent = new ContentValues();
						userContent.put("Weight", weight);
						userContent.put("BodyFat", bodyFat);
						userContent.put("Height", height);
						userContent.put("Waist", waist);
						userContent.put("Chest", chest);
						userContent.put("Arms", arms);
						userContent.put("Forearms", forearms);
						userContent.put("Shoulders", shoulders);
						userContent.put("Hips", hips);
						userContent.put("Thighs", thighs);
						userContent.put("Calves", calves);
						userContent.put("Neck", neck);
						int updateStats = MainActivity.db.update("User",
								userContent, "UserID=?",
								new String[] { UserID });
						if (updateStats > 0) {
							Home.getData();
							UserInformation.getData();
							hideKeyboard(rootView);
						}

						DatabaseUltility.UpdateToLogUser(userContent);

					} else {
						Toast.makeText(
								getActivity(),
								"Body stats value (chest, waist ,arms ,shoulder ,forearms ,neck ,hips , thighs ,calves) must be positive integer and less than 200",
								Toast.LENGTH_LONG).show();
					}
				} catch (Exception ex) {
					Toast.makeText(getActivity(), ex.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}

		});
		
		getData();

		return rootView;
	}

	public static void getData() {
		Cursor bodyStats = MainActivity.db.rawQuery(
				"SELECT * FROM User WHERE UserID = 1", null);

		while (bodyStats.moveToNext()) {
			etWeight.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Weight));
			etBodyFat.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.BodyFat));
			etHeight.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Height));
			etChest.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Chest));
			etWaist.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Waist));
			etArms.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Arms));
			etShoulders.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Shoulders));
			etForearms.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Forearms));
			etNeck.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Neck));
			etHips.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Hips));
			etThighs.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Thighs));
			etCalves.setText(DatabaseUltility.GetColumnValue(bodyStats,
					DatabaseUltility.Calves));
		}
		etWeight.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etWeight.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etBodyFat.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etBodyFat.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etHeight.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etHeight.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etChest.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etChest.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etWaist.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etWaist.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etArms.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etArms.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etShoulders.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etShoulders.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etForearms.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etForearms.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etNeck.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etNeck.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etHips.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etHips.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etThighs.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etThighs.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		etCalves.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(false);
					btnSaveUpdateStats.setTextColor(buttonDisabledText);
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
				if (!etWeight.getText().toString().equals("")
						&& !etBodyFat.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")
						&& !etChest.getText().toString().equals("")
						&& !etWaist.getText().toString().equals("")
						&& !etArms.getText().toString().equals("")
						&& !etShoulders.getText().toString().equals("")
						&& !etForearms.getText().toString().equals("")
						&& !etNeck.getText().toString().equals("")
						&& !etHips.getText().toString().equals("")
						&& !etThighs.getText().toString().equals("")
						&& !etCalves.getText().toString().equals("")) {
					btnSaveUpdateStats.setEnabled(true);
					btnSaveUpdateStats.setTextColor(buttonEnabledText);
				}
			}
		});
		
	}

	public static UpdateBodyStats newInstance(String string) {
		// TODO Auto-generated method stub
		UpdateBodyStats f = new UpdateBodyStats();
		Bundle b = new Bundle();
		b.putString("msg", string);
		f.setArguments(b);

		return f;
	}

	// An ban phim khi bam ra ben ngoai
	protected void hideKeyboard(View view) {
		InputMethodManager in = (InputMethodManager) getActivity()
				.getSystemService(getActivity().INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
