package com.fitnessgear.adapter;

import java.util.ArrayList;

import com.fitnessgear.R;
import com.fitnessgear.adapter.ListExercisesAdapter.ViewHolder;
import com.fitnessgear.model.LogExerciseItem;
import com.fitnessgear.model.LogNoteItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NoteAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<LogNoteItem> listNote;
	
	public NoteAdapter(Context mContext, ArrayList<LogNoteItem> listNote) {
		super();
		this.mContext = mContext;
		this.listNote = listNote;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listNote.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listNote.get(position);
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
		if(convertView == null){
			holder = new ViewHolder();
			LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.note_item, null);
			holder.timeNote = (TextView) convertView.findViewById(R.id.timeNote);
			holder.conentNote = (TextView) convertView.findViewById(R.id.contentNote);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.timeNote.setText(listNote.get(position).getDay());
		holder.conentNote.setText(listNote.get(position).getNoteDetail());
		
		return convertView;
	}
	
	public static class ViewHolder {
		public TextView timeNote;
		public TextView conentNote;
	}

}
