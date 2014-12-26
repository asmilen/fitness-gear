package com.fitnessgear.child;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.database.DatabaseUltility;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class LogBodyStats  extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.activity_log_body_stats,
				container, false);
		String dayID = getArguments().getString("dayID");
		Cursor c = MainActivity.db.rawQuery("Select * from Log Where Day='"+dayID+"'", null);
		if (c.moveToNext())
		{
			EditText etWeight = (EditText) rootView.findViewById(R.id.etWeightBody);
			EditText etBodyFat = (EditText) rootView.findViewById(R.id.etBodyFat);
			EditText etHeight = (EditText) rootView.findViewById(R.id.etHeightBody);
			EditText etChest = (EditText) rootView.findViewById(R.id.etChestBody);
			EditText etWaist = (EditText) rootView.findViewById(R.id.etWaistBody);
			EditText etArms = (EditText) rootView.findViewById(R.id.etArmsBody);
			EditText etShoulders = (EditText) rootView.findViewById(R.id.etShouldersBody);
			EditText etForearms = (EditText) rootView.findViewById(R.id.etForearmsBody);
			EditText etNeck = (EditText) rootView.findViewById(R.id.etNeckBody);
			EditText etHips = (EditText) rootView.findViewById(R.id.etHipsBody);
			EditText etThighs = (EditText) rootView.findViewById(R.id.etThighsBody);
			EditText etCalves = (EditText) rootView.findViewById(R.id.etCalvesBody);
			
			Cursor bodyStats = MainActivity.db.rawQuery(
					"SELECT * FROM User WHERE UserID = "+DatabaseUltility.GetIntColumnValue(c, DatabaseUltility.UserID), null);
			
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
				bodyStats.close();
		}
		}
		c.close();
		return rootView;
	}

	public static Fragment newInstance(String string) {
		// TODO Auto-generated method stub
		LogBodyStats f = new LogBodyStats();
		Bundle b = new Bundle();
		b.putString("dayID", string);
		f.setArguments(b);

		return f;
	}
}
