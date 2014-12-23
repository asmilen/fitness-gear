package com.fitnessgear.adapter;

import java.util.ArrayList;
import java.util.List;

import com.fitnessgear.R;
import com.fitnessgear.model.WorkoutItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

	private Context mContext;
	private final ArrayList<WorkoutItem> item;
	public String startdate;

	public GridAdapter(Context c, ArrayList<WorkoutItem> item) {
		// TODO Auto-generated constructor stub
		mContext = c;
		this.item = item;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return item.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return item.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// convertView = null;
		ViewHolder holder = null;
		WorkoutItem workoutItem = item.get(position);
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			holder = new ViewHolder();
			if (workoutItem.getHeader() == null) {
				convertView = inflater.inflate(R.layout.grid_item, null);
				holder.txtTime = (TextView) convertView
						.findViewById(R.id.txtTime);
				holder.txtExercise = (TextView) convertView
						.findViewById(R.id.txtExercise);
			} else {
				convertView = inflater.inflate(R.layout.list_section, null);
				holder.header = (TextView) convertView
						.findViewById(R.id.header);
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// header = (TextView) grid.findViewById(R.id.header);

		if (workoutItem.getHeader() == null) {
			holder.txtTime.setText(item.get(position).getWorkoutName());
			holder.txtExercise.setText(item.get(position).getDescription());
		} else {
			holder.header.setText(item.get(position).getHeader());
		}
		return convertView;
	}

	public static class ViewHolder {
		public TextView header;
		public TextView txtExercise;
		public TextView txtTime;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		WorkoutItem workoutItem = item.get(position);
		if (workoutItem.getHeader() == null) {
			return true;
		} else {
			return false;
		}
	}
}
