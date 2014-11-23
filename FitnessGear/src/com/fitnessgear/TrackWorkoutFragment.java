package com.fitnessgear;

import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.database.DatabaseUltility;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TrackWorkoutFragment extends Fragment {
	 
	public TrackWorkoutFragment() {
		// TODO Auto-generated constructor stub
	}

	public static TrackWorkoutFragment newInStance(String ExerciseID,String wortkoutID, int position)
	{
		TrackWorkoutFragment myTrack = new TrackWorkoutFragment();
		
		Bundle bundle = new Bundle();
		bundle.putString("ExerciseID", ExerciseID);
		bundle.putString("wortkoutID", wortkoutID);
		bundle.putString("position", position+"");
		myTrack.setArguments(bundle);
		return myTrack;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_track_workout, 
				container,false);
		
		//Set ID
		TextView textviewtargetSet = (TextView)rootView.findViewById(R.id.textViewTargetSets);
		TextView textviewtotalReps = (TextView)rootView.findViewById(R.id.textViewTotalReps);
		TextView textViewEName = (TextView)rootView.findViewById(R.id.textViewEName);
		TextView textViewpostion = (TextView)rootView.findViewById(R.id.textViewPosition);
		
		ImageView img1 = (ImageView)rootView.findViewById(R.id.imageView1);
		ImageView img2 = (ImageView)rootView.findViewById(R.id.imageView2);

		//Get arguments
		String position = getArguments().getString("position");
		String ExerciseID = getArguments().getString("ExerciseID");
		
		
		textViewpostion.setText(position);
		
//		DataBaseHelper helper = new DataBaseHelper(getActivity());
//		MainActivity.db = MainActivity.dbHelper.getReadableDatabase();

		
		Cursor c = MainActivity.db.rawQuery("Select * FROM Exercise Where ExerciseID="+ExerciseID,null);
		while (c.moveToNext())
		{
			textViewEName.setText(DatabaseUltility.GetColumnValue(c, DatabaseUltility.ExerciseName));
					
			//Set img base64
			String strBase64 = c.getString(c.getColumnIndex("Image1"));
			byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
			img1.setImageBitmap(decodedByte);
			
			strBase64 = c.getString(c.getColumnIndex("Image2"));
			decodedString = Base64.decode(strBase64, Base64.DEFAULT);
			decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
			img2.setImageBitmap(decodedByte);
		}
		
		
		return rootView;
	}
}
