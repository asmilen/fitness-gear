package com.fitnessgear;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.fitnessgear.adapter.CalendarAdapter;
import com.fitnessgear.child.LogExerciseDetail;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;
import com.roomorama.caldroid.CaldroidListener;

@SuppressLint("SimpleDateFormat")
public class Logs extends FragmentActivity {

	public final static String DAYID = "dayID";

	public class CalendarCustomFragment extends CaldroidFragment {

		@Override
		public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
			// TODO Auto-generated method stub
			return new CalendarAdapter(getActivity(), month, year,
					getCaldroidData(), extraData);
		}

	}

	private CalendarCustomFragment caldroidFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_logs);

		final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

		// Setup caldroid fragment
		// **** If you want normal CaldroidFragment, use below line ****
		caldroidFragment = new CalendarCustomFragment();

		// If Activity is created after rotation
		if (savedInstanceState != null) {
			caldroidFragment.restoreStatesFromKey(savedInstanceState,
					"CALDROID_SAVED_STATE");
		}
		// If activity is created from fresh
		else {
			Bundle args = new Bundle();
			Calendar cal = Calendar.getInstance();
			args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
			args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
			args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
			args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

			// Uncomment this to customize startDayOfWeek
			// args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
			// CaldroidFragment.TUESDAY); // Tuesday
			caldroidFragment.setArguments(args);
		}

		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();

		// Setup listener
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				try {
					Intent intent = new Intent(getApplicationContext(),
							LogExerciseDetail.class);
					String dayID = date.getDate() + "" + date.getMonth() + ""
							+ (date.getYear()+1900);
					intent.putExtra(DAYID, dayID);
					startActivity(intent);
				} catch (Exception ex) {
					Toast.makeText(getApplicationContext(), ex.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onChangeMonth(int month, int year) {

			}

			@Override
			public void onLongClickDate(Date date, View view) {

			}

			@Override
			public void onCaldroidViewCreated() {

			}

		};

		// Setup Caldroid
		caldroidFragment.setCaldroidListener(listener);

	}

	/**
	 * Save current states of the Caldroid here
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		if (caldroidFragment != null) {
			caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
		}

	}

}
