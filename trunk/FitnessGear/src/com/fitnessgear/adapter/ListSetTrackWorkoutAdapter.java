package com.fitnessgear.adapter;

import com.fitnessgear.R;
import com.fitnessgear.adapter.ListExercisesAdapter.ViewHolder;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

	// For update
	private String Kgs;
	private String Reps;
	private boolean onUpdate = false;

	public String getKgs() {
		return Kgs;
	}

	public void setKgs(String kgs) {
		Kgs = kgs;
	}

	public String getReps() {
		return Reps;
	}

	public boolean isOnUpdate() {
		return onUpdate;
	}

	public void setOnUpdate(boolean onUpdate) {
		this.onUpdate = onUpdate;
	}

	public void setReps(String reps) {
		Reps = reps;
	}

	public ListSetTrackWorkoutAdapter(Context mContext, int nSet,
			String repmin, String repmax) {
		super();
		this.mContext = mContext;
		this.nSet = nSet;
		Repmin = repmin;
		Repmax = repmax;
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
		
		if (holder.targetRep.getText().charAt(0)!='T') onUpdate = true;
		// Intial Text for TextView description
		if (!onUpdate) {
			holder.targetRep.setText("Target Reps: " + Repmin + "-" + Repmax);
			holder.set.setText("Set " + (position + 1));
		} else {
			holder.targetRep.setText(Kgs + "x" + Reps);
			SavetoDatabase();
		}
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
