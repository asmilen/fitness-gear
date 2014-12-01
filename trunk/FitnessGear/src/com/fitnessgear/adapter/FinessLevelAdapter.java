package com.fitnessgear.adapter;

import java.util.ArrayList;

import com.fitnessgear.R;
import com.fitnessgear.model.FilterItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FinessLevelAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<String> mData;
	
	public FinessLevelAdapter(Context mContext, ArrayList<String> mData) {
		super();
		this.mContext = mContext;
		this.mData = mData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView == null){
			LayoutInflater mInflater = (LayoutInflater)
                    mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, null);
		}
		
		TextView titleFilter = (TextView) convertView.findViewById(R.id.titleFilter);
		TextView valueFilter = (TextView) convertView.findViewById(R.id.valueFilter);
		
		return convertView;
	}

}
