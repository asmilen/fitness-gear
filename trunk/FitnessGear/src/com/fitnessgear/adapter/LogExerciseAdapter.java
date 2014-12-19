package com.fitnessgear.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.LogExerciseItem;

public class LogExerciseAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<LogExerciseItem> listExercises = new ArrayList<LogExerciseItem>();
	private ArrayList<Integer> listExercisesID = new ArrayList<Integer>();

	public LogExerciseAdapter(Context mContext,
			ArrayList<LogExerciseItem> listExercises,
			ArrayList<Integer> listExercisesID) {
		super();
		this.mContext = mContext;
		this.listExercises = listExercises;
		this.listExercisesID = listExercisesID;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listExercisesID.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listExercisesID.get(position);
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
			convertView = mInflater.inflate(R.layout.list_log_exercise, null);
			holder.img1 = (ImageView) convertView
					.findViewById(R.id.imageViewExercise);
			holder.ExerciseName = (TextView) convertView
					.findViewById(R.id.textViewExerciseName);
			holder.listSet = (ListView) convertView
					.findViewById(R.id.listViewSet);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// Get img and Exercise Name from Database
		int ExerciseID = listExercisesID.get(position);
		Cursor c = MainActivity.db
				.rawQuery("Select * from Exercise Where ExerciseID = "
						+ ExerciseID, null);
		while (c.moveToNext()) {
			// Decode String image1
			String image1 = DatabaseUltility.GetColumnValue(c,
					DatabaseUltility.Image1);
			byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
					0, decodedString.length);

			// Set image by bitmap
			holder.img1.setImageBitmap(decodedByte);

			// Set Text for TextView description
			holder.ExerciseName.setText(DatabaseUltility.GetColumnValue(c,
					DatabaseUltility.ExerciseName));

		}

		// Set listView

		// Add rep and Kg
		ArrayList<String> listSet = new ArrayList<String>();
		try {

			for (int i = 0; i < listExercises.size(); i++) {
				LogExerciseItem item = listExercises.get(i);
				if (item.getExerciseID() == ExerciseID) {
					listSet.add("Set " + item.getSets() + ": " + item.getKgs()
							+ " kg x " + item.getReps() + " reps");
				}
				//Toast.makeText(mContext, item.getExerciseID()+ " " + item.getSets(),Toast.LENGTH_LONG).show(); 
			}
		} catch (Exception ex) {
			Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

		// Create the array adapter to bind the array to the listView
		ArrayAdapter<String> aa;
		aa = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1, listSet);

		holder.listSet.setAdapter(aa);
		return convertView;
	}

	public static class ViewHolder {
		public ImageView img1;
		public TextView ExerciseName;
		public ListView listSet;
	}

}
