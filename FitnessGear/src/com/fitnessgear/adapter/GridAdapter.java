package com.fitnessgear.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.fitnessgear.R;
import com.fitnessgear.model.GridItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

	private Context mContext;
	private final ArrayList<GridItem> item;
	private TextView txtTime;
	private TextView txtExercise;
	private TextView header;
	
	
	public GridAdapter(Context c,ArrayList<GridItem> item) {
		// TODO Auto-generated constructor stub
		mContext = c;
		this.item = item;
	}
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return item.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View grid;
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			grid = new View(mContext);
			grid = inflater.inflate(R.layout.grid_item, null);
			
		}
		else{
			grid = (View) convertView;
		}
//		header = (TextView) grid.findViewById(R.id.header);
		
		
		txtTime = (TextView) grid.findViewById(R.id.txtTime);
		txtTime.setText(item.get(position).getTxtTime());
		
		txtExercise = (TextView) grid.findViewById(R.id.txtExercise);
		txtExercise.setText(item.get(position).getTxtExercise());
		
		return grid;
	}

}
