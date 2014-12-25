package com.fitnessgear;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SetupAccount extends Activity {

	private EditText etUserName;
	private EditText etDoB;
	private EditText etWeight;
	private EditText etHeight;
	private Spinner spGender;
	private Button SaveProfileUser;
	
	private DatePickerDialog datePicker;
	
	private boolean validateAge = false;
	
	private boolean doubleBackToExitPressOnce = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_account);
		
		etUserName = (EditText) findViewById(R.id.etUserNameFirstActivity);
		etDoB = (EditText) findViewById(R.id.etDoBFirstActivity);
		etWeight = (EditText) findViewById(R.id.etWeightFirstActivity);
		etHeight = (EditText) findViewById(R.id.etHeightFirstActivity);
		spGender = (Spinner) findViewById(R.id.spGenderFirstActivity);
		SaveProfileUser = (Button) findViewById(R.id.SaveProfileUser);
		SaveProfileUser.setEnabled(false);
		SaveProfileUser.setTextColor(getResources().getColor(R.color.button_text));
		etUserName.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(etUserName.getText().toString().equals("")){
					SaveProfileUser.setEnabled(false);
					SaveProfileUser.setTextColor(getResources().getColor(R.color.button_text));
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
				if(!etUserName.getText().toString().equals("")
						&& !etDoB.getText().toString().equals("")
						&& !etWeight.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")){
					SaveProfileUser.setEnabled(true);
					SaveProfileUser.setTextColor(getResources().getColor(R.color.text_title_color));
				}
					
			}
		});
		etDoB.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(etDoB.getText().toString().equals("")){
					SaveProfileUser.setEnabled(false);
					SaveProfileUser.setTextColor(getResources().getColor(R.color.button_text));
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
				if(!etUserName.getText().toString().equals("")
						&& !etDoB.getText().toString().equals("")
						&& !etWeight.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")){
					SaveProfileUser.setEnabled(true);
					SaveProfileUser.setTextColor(getResources().getColor(R.color.text_title_color));
				}
			}
		});
		etWeight.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(etWeight.getText().toString().equals("")){
					SaveProfileUser.setEnabled(false);
					SaveProfileUser.setTextColor(getResources().getColor(R.color.button_text));
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
				if(!etUserName.getText().toString().equals("")
						&& !etDoB.getText().toString().equals("")
						&& !etWeight.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")){
					SaveProfileUser.setEnabled(true);
					SaveProfileUser.setTextColor(getResources().getColor(R.color.text_title_color));
				}
			}
		});
		etHeight.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(etHeight.getText().toString().equals("")){
					SaveProfileUser.setEnabled(false);
					SaveProfileUser.setTextColor(getResources().getColor(R.color.button_text));
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
				if(!etUserName.getText().toString().equals("")
						&& !etDoB.getText().toString().equals("")
						&& !etWeight.getText().toString().equals("")
						&& !etHeight.getText().toString().equals("")){
					SaveProfileUser.setEnabled(true);
					SaveProfileUser.setTextColor(getResources().getColor(R.color.text_title_color));
				}
			}
		});
		// Get current Date
				final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
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
				int currentYear = cal.get(Calendar.YEAR);
				if(currentYear - year > 0){
					validateAge = true;
				}
				if(currentYear - year < 0){
					validateAge = false;
				}
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
		

		// Create Date Picker Dialog
		if (!etDoB.getText().toString().equals("")) {
			String[] dob = etDoB.getText().toString().split("/");
			datePicker = new DatePickerDialog(SetupAccount.this,
					datePickerListener, Integer.valueOf(dob[2]),
					Integer.valueOf(dob[1]) - 1,
					Integer.valueOf(dob[0]));
		} else {
			datePicker = new DatePickerDialog(SetupAccount.this,
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
		
		ArrayList<String> arrayGender = new ArrayList<String>();
		arrayGender.add("Male");
		arrayGender.add("Female");
		
		ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(SetupAccount.this, android.R.layout.simple_spinner_item,arrayGender);
		
		spGender.setAdapter(adapterGender);
		
		SaveProfileUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				float weight = Float.parseFloat(etWeight.getText().toString());
				float height = Float.parseFloat(etHeight.getText().toString());
				if(weight>=0 && weight <= 200
						&& height>=0 && height <= 300
						&& validateAge){
					MainActivity.db = MainActivity.dbHelper.getWritableDatabase();
					ContentValues contentUser = new ContentValues();
					contentUser.put("UserName", etUserName.getText().toString());
					contentUser.put("DateOfBirth", etDoB.getText().toString());
					contentUser.put("Gender", spGender.getSelectedItem().toString());
					contentUser.put("Weight", weight);
					contentUser.put("Height", height);
					int createUser = MainActivity.db.update("User", contentUser, "UserID = 1", null);
					if(createUser > 0){
						Toast.makeText(SetupAccount.this, "Create User Successfull", Toast.LENGTH_LONG).show();
						Home.getData();
						UserInformation.getData();
						UpdateBodyStats.getData();
						SetupAccount.this.finish();
					}
				}
				if(!validateAge){
					Toast.makeText(getApplicationContext(), "Age too young to practice", Toast.LENGTH_LONG).show();
				}
				if(weight < 0 || weight > 200){
					Toast.makeText(getApplicationContext(), "Weight must be greater than 0 Kg and less than 200 Kg", Toast.LENGTH_LONG).show();
				}
				if(height < 0 || height > 300){
					Toast.makeText(getApplicationContext(), "Height must be greater than 0 Cm and less than 300 Cm", Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (doubleBackToExitPressOnce) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
	        return;
	    }

	    this.doubleBackToExitPressOnce = true;
	    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

	    new Handler().postDelayed(new Runnable() {

	        @Override
	        public void run() {
	        	doubleBackToExitPressOnce=false;                       
	        }
	    }, 2000);
		
	}
}
