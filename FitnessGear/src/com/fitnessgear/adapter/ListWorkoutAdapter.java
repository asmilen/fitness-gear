package com.fitnessgear.adapter;

import java.util.ArrayList;

import com.fitnessgear.R;
import com.fitnessgear.adapter.GridAdapter.ViewHolder;
import com.fitnessgear.model.GridItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListWorkoutAdapter extends BaseAdapter {

	private Context mContext;
	private final ArrayList<GridItem> item;

	public ListWorkoutAdapter(Context c, ArrayList<GridItem> item) {
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
		ViewHolder holder = null;
		GridItem gridItem = item.get(position);
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			holder = new ViewHolder();
			if (gridItem.getHeader() == null) {
				convertView = inflater.inflate(R.layout.grid_item, null);
				holder.txtTime = (TextView) convertView.findViewById(R.id.txtTime);
//				holder.txtExercise = (TextView) convertView.findViewById(R.id.txtExercise);
			}
			else {
				convertView = inflater.inflate(R.layout.list_section, null);
				holder.header = (TextView) convertView.findViewById(R.id.header);
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// header = (TextView) grid.findViewById(R.id.header);

		if (gridItem.getHeader() == null) {
			holder.txtTime.setText(item.get(position).getTxtTime());
//			holder.txtExercise.setText(item.get(position).getTxtExercise());
		} else {
			holder.header.setText(item.get(position).getHeader());
		}

		return convertView;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		GridItem gridItem = item.get(position);
		if (gridItem.getHeader() == null) {
			return true;
		} else {
			return false;
		}
	}
}
