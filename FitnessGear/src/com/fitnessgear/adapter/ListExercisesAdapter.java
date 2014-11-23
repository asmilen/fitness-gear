package com.fitnessgear.adapter;

import java.util.ArrayList;

import com.fitnessgear.R;
import com.fitnessgear.model.ListExercisesItem;

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
import android.widget.TextView;

public class ListExercisesAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<ListExercisesItem> listExercises;
	
	public ListExercisesAdapter(Context mContext,ArrayList<ListExercisesItem> listExercises) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.listExercises = listExercises;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listExercises.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listExercises.get(position);
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
            convertView = mInflater.inflate(R.layout.list_exercises_item, null);
            holder.img1 = (ImageView) convertView.findViewById(R.id.img1);
            holder.img2 = (ImageView) convertView.findViewById(R.id.img2);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(holder);
        }
		else {
			holder = (ViewHolder) convertView.getTag();
		}
         
        
        
        //Decode String image1
        String image1 = listExercises.get(position).getImg1();
		byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
		Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        
		//Set image by bitmap
        holder.img1.setImageBitmap(decodedByte);
        
        //Decode String image2
        String image2 = listExercises.get(position).getImg2();
        decodedString = Base64.decode(image2, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        
        //Set image by bitmap
        holder.img2.setImageBitmap(decodedByte);
        
        //Set Text for TextView description
        holder.description.setText(listExercises.get(position).getDescription());
		return convertView;
	}
	
	public static class ViewHolder {
		public ImageView img1;
		public ImageView img2;
		public TextView description;
	}

}
