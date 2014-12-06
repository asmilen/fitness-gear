package com.fitnessgear.child;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import com.fitnessgear.R;
import com.fitnessgear.R.id;
import com.fitnessgear.R.layout;
import com.fitnessgear.R.menu;
import com.fitnessgear.R.style;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddPlan extends Activity {

	private DatePickerDialog datePicker;
	private DatePicker datePickerDia;
	
	private Calendar myCalendar;
	private int PlanID = 1000;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_plan);

		planName = (EditText) findViewById(R.id.txtPlanName);
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
		
		datePickerDia = (DatePicker) findViewById(R.id.datePicker);

		DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

			// when dialog box is closed, below method will be called.
			public void onDateSet(DatePicker view, int selectedYear,
					int selectedMonth, int selectedDay) {
				String year1 = String.valueOf(selectedYear);
				String month1 = String.valueOf(selectedMonth + 1);
				String day1 = String.valueOf(selectedDay);
				dateCreated.setText(day1 + "/" + month1 + "/" + year1);

			}
		};
		try {
			Calendar cal = Calendar.getInstance(TimeZone.getDefault());
			datePicker = new DatePickerDialog(
					getApplicationContext(),
					datePickerListener,
					cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH),
					cal.get(Calendar.DAY_OF_MONTH));
//			datePicker.setCancelable(false);
			datePicker.setTitle("Select the date");
			

		

//		dateCreated.setOnFocusChangeListener(new OnFocusChangeListener() {
//
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				// TODO Auto-generated method stub
//				if (hasFocus) {
//					datePicker.show();
//				}
//				if (hasFocus == false) {
//					datePicker.hide();
//				}
//			}
//		});
		
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
		}
	}

	private void updateLabel() {

		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		dateCreated.setText(sdf.format(myCalendar.getTime()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_plan, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
