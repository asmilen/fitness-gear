package com.fitnessgear.adapter;

import hirondelle.date4j.DateTime;

import java.util.HashMap;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarAdapter extends CaldroidGridAdapter {

	public CalendarAdapter(Context context, int month, int year,
			HashMap<String, Object> caldroidData,
			HashMap<String, Object> extraData) {
		super(context, month, year, caldroidData, extraData);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View cellView = convertView;

		try
		{
		// For reuse
		if (convertView == null) {
			cellView = inflater.inflate(R.layout.custom_cell_calendar, null);
		}

		int topPadding = cellView.getPaddingTop();
		int leftPadding = cellView.getPaddingLeft();
		int bottomPadding = cellView.getPaddingBottom();
		int rightPadding = cellView.getPaddingRight();

		TextView tv1 = (TextView) cellView
				.findViewById(R.id.textViewCustomCell1);

		ImageView img = (ImageView) cellView
				.findViewById(R.id.imageViewCustomCell);
		ImageView imgNote = (ImageView) cellView
				.findViewById(R.id.imageViewCustomCellNote);
		ImageView imgBody = (ImageView) cellView
				.findViewById(R.id.imageViewCustomCellBody);

		tv1.setTextColor(Color.BLACK);

		// Get dateTime of this cell
		DateTime dateTime = this.datetimeList.get(position);
		Resources resources = context.getResources();

		// Set color of the dates in previous / next month
		if (dateTime.getMonth() != month) {
			tv1.setTextColor(resources
					.getColor(com.caldroid.R.color.caldroid_darker_gray));
		}

		boolean shouldResetDiabledView = false;
		boolean shouldResetSelectedView = false;

		// Customize for disabled dates and date outside min/max dates
		if ((minDateTime != null && dateTime.lt(minDateTime))
				|| (maxDateTime != null && dateTime.gt(maxDateTime))
				|| (disableDates != null && disableDates.indexOf(dateTime) != -1)) {

			tv1.setTextColor(CaldroidFragment.disabledTextColor);
			if (CaldroidFragment.disabledBackgroundDrawable == -1) {
				cellView.setBackgroundResource(com.caldroid.R.drawable.disable_cell);
			} else {
				cellView.setBackgroundResource(CaldroidFragment.disabledBackgroundDrawable);
			}

			if (dateTime.equals(getToday())) {
				cellView.setBackgroundResource(com.caldroid.R.drawable.red_border_gray_bg);
			}

		} else {
			shouldResetDiabledView = true;
		}

		// Customize for selected dates
		if (selectedDates != null && selectedDates.indexOf(dateTime) != -1) {
			if (CaldroidFragment.selectedBackgroundDrawable != -1) {
				cellView.setBackgroundResource(CaldroidFragment.selectedBackgroundDrawable);
			} else {
				cellView.setBackgroundColor(resources
						.getColor(com.caldroid.R.color.caldroid_sky_blue));
			}

			tv1.setTextColor(CaldroidFragment.selectedTextColor);

		} else {
			shouldResetSelectedView = true;
		}

		if (shouldResetDiabledView && shouldResetSelectedView) {
			// Customize for today
			if (dateTime.equals(getToday())) {
				cellView.setBackgroundResource(com.caldroid.R.drawable.red_border);
			} else {
				cellView.setBackgroundResource(com.caldroid.R.drawable.cell_bg);
			}
		}

		tv1.setText("" + dateTime.getDay());

		String dayID = dateTime.getDay() + "/" + dateTime.getMonth() + "/"
				+ dateTime.getYear();
		Cursor c = MainActivity.db.rawQuery(
				"Select * from Log_Exercise where Day = '" + dayID + "'", null);
		if (c.moveToNext()) {
			img.setVisibility(View.VISIBLE);
		}

		Cursor c1 = MainActivity.db.rawQuery("Select * from Log_Note where Day = '"
				+ dayID + "'", null);
		if (c1.moveToNext()) {
			imgNote.setVisibility(View.VISIBLE);
		}

		// Set custom color if required
		setCustomResources(dateTime, cellView, tv1);
		cellView.setPadding(leftPadding, topPadding, rightPadding,
				bottomPadding);
		}
		catch (Exception ex)
		{
			Log.e("", ex.getMessage());
		}
		return cellView;
	}

}
