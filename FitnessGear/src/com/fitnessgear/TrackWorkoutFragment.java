package com.fitnessgear;

import com.fitnessgear.database.DataBaseHelper;

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

	public static TrackWorkoutFragment newInStance(String id)
	{
		TrackWorkoutFragment myTrack = new TrackWorkoutFragment();
		
		Bundle bundle = new Bundle();
		bundle.putString("id", id);
		myTrack.setArguments(bundle);
		return myTrack;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_track_workout, 
				container,false);
		TextView textview = (TextView)rootView.findViewById(R.id.textView3);
		TextView textview1 = (TextView)rootView.findViewById(R.id.textView1);
		TextView textview2 = (TextView)rootView.findViewById(R.id.textView2);
		
		ImageView img1 = (ImageView)rootView.findViewById(R.id.imageView1);
		ImageView img2 = (ImageView)rootView.findViewById(R.id.imageView2);

		String id = getArguments().getString("id");
		textview.setText(id);
		
//		DataBaseHelper helper = new DataBaseHelper(getActivity());
		MainActivity.db = MainActivity.dbHelper.getReadableDatabase();
		
		Cursor c = MainActivity.db.rawQuery("Select * FROM Exercise Where ExerciseID="+id,null);
		while (c.moveToNext())
		{
			textview.setText(c.getString(c.getColumnIndex("Description")));
			textview1.setText(c.getString(c.getColumnIndex("ExerciseName")));
			textview2.setText(c.getString(c.getColumnIndex("MuscleTarget")));
			
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
