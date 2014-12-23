package com.fitnessgear.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.model.PlanItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListExercisesAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<ExercisesItem> listExercises;
	private ArrayList<ExercisesItem> filterExercise;

	public ImageView imageExercise;

	ProgressDialog pDialog;
	Bitmap bitmap;
	private String IMAGE_FILEPATH;

	public ListExercisesAdapter(Context mContext,
			ArrayList<ExercisesItem> listExercises) {
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
			LayoutInflater mInflater = (LayoutInflater) mContext
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.list_exercises_item, null);
			holder.img1 = (ImageView) convertView.findViewById(R.id.img1);
			holder.img2 = (ImageView) convertView.findViewById(R.id.img2);
			holder.exerciseName = (TextView) convertView
					.findViewById(R.id.exerciseName);
			holder.rating = (TextView) convertView.findViewById(R.id.txtRating);
			holder.muscle = (TextView) convertView.findViewById(R.id.txtMuscle);
			holder.equipment = (TextView) convertView
					.findViewById(R.id.txtEquipment);
			holder.exerciseType = (TextView) convertView
					.findViewById(R.id.txtExerciseType);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String image1 = listExercises.get(position).getImg1();
		String image2 = listExercises.get(position).getImg2();

		// Decode String image1
		 byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
		 Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,
		 decodedString.length);
		
//		  Set image by bitmap
		 holder.img1.setImageBitmap(decodedByte);

		 // Decode String image2
		 decodedString = Base64.decode(image2, Base64.DEFAULT);
		 decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,
		 decodedString.length);
		
		 // Set image by bitmap
		 holder.img2.setImageBitmap(decodedByte);

		// Set Text for TextView exerciseName
		// Text so long then display ...
		if (listExercises.get(position).getExerciseName().length() > 50) {

		}
		holder.exerciseName.setText(listExercises.get(position)
				.getExerciseName());
		holder.rating.setText("" + listExercises.get(position).getRating());
		Cursor muscleTarget = MainActivity.db.rawQuery(
				"SELECT * FROM Muscles WHERE MuscleID = "
						+ listExercises.get(position).getMuscleTarget(), null);
		while (muscleTarget.moveToNext()) {
			holder.muscle.setText(DatabaseUltility.GetColumnValue(muscleTarget,
					DatabaseUltility.MuscleName));
		}
		Cursor equipment = MainActivity.db.rawQuery(
				"SELECT * FROM Equipment WHERE EquipmentID = "
						+ listExercises.get(position).getEquipment(), null);
		while (equipment.moveToNext()) {
			holder.equipment.setText(DatabaseUltility.GetColumnValue(equipment,
					DatabaseUltility.EquipmentName));
		}
		Cursor exerciseType = MainActivity.db.rawQuery(
				"SELECT * FROM ExerciseType WHERE ExerciseTypeID = "
						+ listExercises.get(position).getExerciseType(), null);
		while (exerciseType.moveToNext()) {
			holder.exerciseType.setText(DatabaseUltility.GetColumnValue(
					exerciseType, DatabaseUltility.ExerciseTypeName));
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

//	private class LoadImage extends AsyncTask<Object, String, Bitmap> {
//		private ImageView img;
//	    private Bitmap bitmap = null;
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//		}
//
//		protected Bitmap doInBackground(Object... args) {
//			try {
////				bitmap = BitmapFactory.decodeStream((InputStream) new URL(
////						args[0]).getContent());
//				img = (ImageView) args[0];
//				String path = (String) args[1];
//				Bitmap rootBitmap = BitmapFactory.decodeStream((InputStream) new URL(path).getContent());
//				bitmap = Bitmap.createScaledBitmap(rootBitmap, 90, 90, false);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return bitmap;
//		}
//
//		protected void onPostExecute(Bitmap image) {
//			if (bitmap != null) {
//				img.setImageBitmap(bitmap);
//	        }
//		}
//	}

	// Filter Class
	public void filter(String textFilter, int exerciseTypeID, int muscleID,
			int equipmentID) {

		// VIet Ham Filter vao day
		// Khi Select tu bang Plan phai select tu bang
		// Gender,FitnessLevel,Main_Goal de lay ra Name cua cac bang
		// Giong voi String sql o duoi
		// Can add vao bien listPlan sau do goi ham notifyDataSetChanged()

		textFilter = textFilter.toLowerCase(Locale.getDefault());

		// BUILD QUERY WITH FILTER
		String sql = "Select * from Exercise Where ";
		if (exerciseTypeID != 1)
			sql += "ExerciseType = " + exerciseTypeID + " AND ";
		if (muscleID != 1)
			sql += "MuscleTarget = " + muscleID + " AND ";
		if (equipmentID != 1)
			sql += "Equipment = " + equipmentID + " AND ";
		if (textFilter != "") {
			sql += "ExerciseName LIKE '%" + textFilter + "%' AND ";
		}

		// Delete last AND
		String[] sql1 = sql.split(" ");
		StringBuilder builder = new StringBuilder();
		String delimiter = " ";
		for (int i = 0; i < sql1.length - 1; i++) {
			builder.append(sql1[i]);
			builder.append(delimiter);
		}
		sql = builder.toString();

		listExercises.clear();

		// Exec query and add to listPlan
		Cursor c = MainActivity.db.rawQuery(sql, null);
		while (c.moveToNext()) {
			// String mainGoalName =
			// getGoalName(DatabaseUltility.GetColumnValue(c,
			// DatabaseUltility.MainGoal));
			// String genderName =
			// getGenderName(DatabaseUltility.GetColumnValue(c,
			// DatabaseUltility.Gender));
			// String fitnessLevelName =
			// getFitnessLevelName(DatabaseUltility.GetColumnValue(c,
			// DatabaseUltility.FitnessLevel));
			listExercises
					.add(new ExercisesItem(DatabaseUltility.GetIntColumnValue(
							c, "Workout_Exercise."
									+ DatabaseUltility.ExerciseID),
							DatabaseUltility.GetColumnValue(c,
									DatabaseUltility.ExerciseName),
							DatabaseUltility.GetIntColumnValue(c,
									DatabaseUltility.ExerciseType),
							DatabaseUltility.GetIntColumnValue(c,
									DatabaseUltility.MuscleTarget),
							DatabaseUltility.GetIntColumnValue(c,
									DatabaseUltility.Equipment),
							DatabaseUltility.GetFloatColumnValue(c,
									DatabaseUltility.Rating),
							DatabaseUltility.GetColumnValue(c,
									DatabaseUltility.Image1),
							DatabaseUltility.GetColumnValue(c,
									DatabaseUltility.Image2), DatabaseUltility
									.GetColumnValue(c,
											DatabaseUltility.Description)));

		}
		notifyDataSetChanged();
	}
}
