package com.fitnessgear;

import com.fitnessgear.adapter.ListSetTrackWorkoutAdapter;
import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.database.DatabaseUltility;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FindAPlan extends Fragment {

	public FindAPlan() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_find_a_plan,
				container, false);

		Cursor c = MainActivity.db.rawQuery("Select * from Log_Exercise", null);
		while (c.moveToNext()) {
			String Day = DatabaseUltility.GetColumnValue(c,
					DatabaseUltility.Day);
			String E = DatabaseUltility.GetColumnValue(c,
					DatabaseUltility.ExerciseID);
			String S = DatabaseUltility
					.GetColumnValue(c, DatabaseUltility.Sets);
			String R = DatabaseUltility
					.GetColumnValue(c, DatabaseUltility.Reps);
			String K = DatabaseUltility.GetColumnValue(c, DatabaseUltility.Kg);

			Toast.makeText(getActivity(), Day + " " + E + S + R + K,
					Toast.LENGTH_LONG).show();
		}
		return rootView;
	}
}
