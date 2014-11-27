package com.fitnessgear.adapter;

import java.util.ArrayList;

import com.fitnessgear.R;
import com.fitnessgear.adapter.ListExercisesAdapter.ViewHolder;
import com.fitnessgear.model.LogExerciseItem;
import com.fitnessgear.model.LogExerciseList;
import com.google.gson.Gson;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListSetTrackWorkoutAdapter extends BaseAdapter {

	private Context mContext;
	private int nSet;
	private String Repmin;
	private String Repmax;
	private int ExerciseID;

	public ListSetTrackWorkoutAdapter(Context mContext, int nSet,
			String repmin, String repmax, int exerciseID) {
		super();
		this.mContext = mContext;
		this.nSet = nSet;
		Repmin = repmin;
		Repmax = repmax;
		ExerciseID = exerciseID;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return nSet;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		// Get ID
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater mInflater = (LayoutInflater) mContext
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.list_set_trackworkout,
					null);
			holder.img = (ImageView) convertView
					.findViewById(R.id.imageViewTick);
			holder.targetRep = (TextView) convertView
					.findViewById(R.id.textViewReps);
			holder.set = (TextView) convertView.findViewById(R.id.textViewSet);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// Set Text Set
		holder.set.setText("Set " + (position + 1));

		// Get shared preference
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(mContext);
		Gson gson = new Gson();
		String json = settings.getString("logexerciselist", "");
		LogExerciseList mylist = gson.fromJson(json, LogExerciseList.class);

		ArrayList<LogExerciseItem> list = mylist.getMyLogExerciseList();
		
		// Lay ra gia tri trong list
		LogExerciseItem item = new LogExerciseItem(0, 0, 0);
		for (int i = 0; i < list.size(); i++) {
			item = list.get(i);
			if (item.getExerciseID() == ExerciseID
					&& item.getSets() == (position + 1))
				break;
		}

		if (item.getReps() == 0) {
			holder.targetRep.setText("Target Reps: " + Repmin + "-" + Repmax);
		} else {
			if (item.getKgs() == 0) {
				holder.targetRep.setText(item.getReps() + "");
			} else {
				holder.targetRep.setText(item.getKgs() + "x" + item.getReps());
			}
		}
		SavetoDatabase();

		return convertView;
	}

	private void SavetoDatabase() {
		// TODO Auto-generated method stub

	}

	public static class ViewHolder {
		public ImageView img;
		public TextView targetRep;
		public TextView set;
	}
}
