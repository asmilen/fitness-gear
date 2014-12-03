package com.fitnessgear.adapter;

import java.util.ArrayList;
import java.util.Locale;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
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
	private ArrayList<ExercisesItem> listExercises;
	private ArrayList<ExercisesItem> filterExercise;
	
	public ListExercisesAdapter(Context mContext,ArrayList<ExercisesItem> listExercises) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.listExercises = listExercises;
		this.filterExercise = new ArrayList<ExercisesItem>();
		this.filterExercise.addAll(listExercises);
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
            holder.exerciseName = (TextView) convertView.findViewById(R.id.exerciseName);
            holder.rating = (TextView) convertView.findViewById(R.id.txtRating);
            holder.muscle = (TextView) convertView.findViewById(R.id.txtMuscle);
            holder.equipment = (TextView) convertView.findViewById(R.id.txtEquipment);
            holder.exerciseType = (TextView) convertView.findViewById(R.id.txtExerciseType);
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
        
        //Set Text for TextView exerciseName
        holder.exerciseName.setText(listExercises.get(position).getExerciseName());
        holder.rating.setText(""+listExercises.get(position).getRating());
        Cursor muscleTarget = MainActivity.db.rawQuery("SELECT * FROM Muscles WHERE MuscleID = " + listExercises.get(position).getMuscleTarget(), null);
		while (muscleTarget.moveToNext()) {
			holder.muscle.setText(DatabaseUltility.GetColumnValue(muscleTarget, DatabaseUltility.MuscleName));	
		}
		Cursor equipment = MainActivity.db.rawQuery("SELECT * FROM Equipment WHERE EquipmentID = " + listExercises.get(position).getEquipment(), null);
		while (equipment.moveToNext()) {
			holder.equipment.setText(DatabaseUltility.GetColumnValue(equipment, DatabaseUltility.EquipmentName));	
		}
		Cursor exerciseType = MainActivity.db.rawQuery("SELECT * FROM ExerciseType WHERE ExerciseTypeID = " + listExercises.get(position).getExerciseType(), null);
		while (exerciseType.moveToNext()) {
			holder.exerciseType.setText(DatabaseUltility.GetColumnValue(exerciseType, DatabaseUltility.ExerciseTypeName));	
		}
        return convertView;
	}
	
	public static class ViewHolder {
		public ImageView img1;
		public ImageView img2;
		public TextView exerciseName;
		public TextView rating;
		public TextView muscle;
		public TextView equipment;
		public TextView exerciseType;
	}
	
	// Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listExercises.clear();
        if (charText.length() == 0) {
            listExercises.addAll(filterExercise);
        } 
        else 
        {
            for (ExercisesItem exerciseItem : filterExercise) 
            {
                if (exerciseItem.getExerciseName().toLowerCase(Locale.getDefault()).contains(charText)) 
                {
                    listExercises.add(exerciseItem);
                }
                else if (exerciseItem.getExerciseType() == 1) 
                {
                    listExercises.add(exerciseItem);
                }
            }
        }
        notifyDataSetChanged();
    }

}
