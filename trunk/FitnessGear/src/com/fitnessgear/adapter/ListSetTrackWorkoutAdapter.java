package com.fitnessgear.adapter;

import com.fitnessgear.R;
import com.fitnessgear.adapter.ListExercisesAdapter.ViewHolder;

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
	private String nSet;
	private String Repmax;
	private String Repmin;
	
	
	
	
	public ListSetTrackWorkoutAdapter(Context mContext, String nSet,
			String repmax, String repmin) {
		super();
		this.mContext = mContext;
		this.nSet = nSet;
		Repmax = repmax;
		Repmin = repmin;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.valueOf(nSet);
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
            LayoutInflater mInflater = (LayoutInflater)
                    mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_set_trackworkout, null);
            holder.radio = (RadioButton) convertView.findViewById(R.id.radioButton1);
            holder.targetSet = (TextView) convertView.findViewById(R.id.textViewSet);
            convertView.setTag(holder);
        }
		else {
			holder = (ViewHolder) convertView.getTag();
		}
               
              
        //Set Text for TextView description
        holder.targetSet.setText("Target Sets: "+Repmin + "-" + Repmax);
        holder.radio.setText("Set " + String.valueOf(position));
		return convertView;
	}

	public static class ViewHolder {
		public RadioButton radio;
		public TextView targetSet;
	}
}
