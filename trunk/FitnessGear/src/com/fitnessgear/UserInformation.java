package com.fitnessgear;

import com.fitnessgear.database.DatabaseUltility;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UserInformation extends Fragment {
	
	private static TextView tvWeight;
	private static TextView tvBodyFat;
	private static TextView tvHeight;
	private static TextView tvChest;
	private static TextView tvWaist;
	private static TextView tvArms;
	private static TextView tvShoulders;
	private static TextView tvForearms;
	private static TextView tvNeck;
	private static TextView tvHips;
	private static TextView tvThighs;
	private static TextView tvCalves;
	
	private static float bodyWeight;
	private static float bodyBodyFat;
	private static float bodyHeight;
	private static float bodyChest;
	private static float bodyWaist;
	private static float bodyArms;
	private static float bodyShoulders;
	private static float bodyForearms;
	private static float bodyNeck;
	private static float bodyHips;
	private static float bodyThighs;
	private static float bodyCalves;
	
	private static float goalWeight;
	private static float goalBodyFat;
	private static float goalHeight;
	private static float goalChest;
	private static float goalWaist;
	private static float goalArms;
	private static float goalShoulders;
	private static float goalForearms;
	private static float goalNeck;
	private static float goalHips;
	private static float goalThighs;
	private static float goalCalves;
	
	private ProgressBar progressWeight;

	private String UserID = "1";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_user_information, container, false);
		
		tvWeight = (TextView) rootView.findViewById(R.id.tvWeight);
		tvBodyFat = (TextView) rootView.findViewById(R.id.tvBodyFat);
		tvHeight = (TextView) rootView.findViewById(R.id.tvHeight);
		tvChest = (TextView) rootView.findViewById(R.id.tvChest);
		tvWaist = (TextView) rootView.findViewById(R.id.tvWaist);
		tvArms = (TextView) rootView.findViewById(R.id.tvArms);
		tvShoulders = (TextView) rootView.findViewById(R.id.tvShoulders);
		tvForearms = (TextView) rootView.findViewById(R.id.tvForearms);
		tvNeck = (TextView) rootView.findViewById(R.id.tvNeck);
		tvHips = (TextView) rootView.findViewById(R.id.tvHips);
		tvThighs = (TextView) rootView.findViewById(R.id.tvThighs);
		tvCalves = (TextView) rootView.findViewById(R.id.tvCalves);
		getData();
		return rootView;
	}
	
	public static void getData(){
		Cursor bodyStats = MainActivity.db.rawQuery("SELECT * FROM User WHERE UserID = 1", null);
		while(bodyStats.moveToNext()){
			bodyWeight = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Weight);
			bodyBodyFat = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.BodyFat);
			bodyHeight = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Height);
			bodyChest = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Chest);
			bodyWaist = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Waist);
			bodyArms = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Arms);
			bodyShoulders = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Shoulders);
			bodyForearms = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Forearms);
			bodyNeck = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Neck);
			bodyHips = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Hips);
			bodyThighs = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Thighs);
			bodyCalves = DatabaseUltility.GetFloatColumnValue(bodyStats, DatabaseUltility.Calves);
			
		}
		Cursor bodyGoals = MainActivity.db.rawQuery("SELECT * FROM User WHERE UserID = 2", null);
		while(bodyGoals.moveToNext()){
			goalWeight = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Weight);
			goalBodyFat = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.BodyFat);
			goalHeight = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Height);
			goalChest = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Chest);
			goalWaist = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Waist);
			goalArms = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Arms);
			goalShoulders = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Shoulders);
			goalForearms = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Forearms);
			goalNeck = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Neck);
			goalHips = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Hips);
			goalThighs = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Thighs);
			goalCalves = DatabaseUltility.GetFloatColumnValue(bodyGoals, DatabaseUltility.Calves);
			
		}
		if(goalWeight == 0){
			tvWeight.setText(bodyWeight + " kg");
		}
		else{
			tvWeight.setText(bodyWeight + "/" + goalWeight + " kg");
		}
		if(goalBodyFat == 0){
			tvBodyFat.setText(bodyBodyFat + " %");
		}
		else{
			tvBodyFat.setText(bodyBodyFat + "/" + goalBodyFat + " %");
		}
		if(goalHeight == 0){
			tvHeight.setText(bodyHeight + " cm");
		}
		else{
			tvHeight.setText(bodyHeight + "/" + goalHeight + " cm");
		}
		if(goalChest == 0){
			tvChest.setText(bodyChest + " cm");
		}
		else{
			tvChest.setText(bodyChest + "/" + goalChest + " cm");
		}
		if(goalWaist == 0){
			tvWaist.setText(bodyWaist + " cm");
		}
		else{
			tvWaist.setText(bodyWaist + "/" + goalWaist + " cm");
		}
		if(goalArms == 0){
			tvArms.setText(bodyArms + " cm");
		}
		else{
			tvArms.setText(bodyArms + "/" + goalArms + " cm");
		}
		if(goalShoulders == 0){
			tvShoulders.setText(bodyShoulders + " cm");
		}
		else{
			tvShoulders.setText(bodyShoulders + "/" + goalShoulders + " cm");
		}
		if(goalForearms == 0){
			tvForearms.setText(bodyForearms + " cm");
		}
		else{
			tvForearms.setText(bodyForearms + "/" + goalForearms + " cm");
		}
		if(goalNeck == 0){
			tvNeck.setText(bodyNeck + " cm");
		}
		else{
			tvNeck.setText(bodyNeck + "/" + goalNeck + " cm");
		}
		if(goalHips == 0){
			tvHips.setText(bodyHips + " cm");
		}
		else{
			tvHips.setText(bodyHips + "/" + goalHips + " cm");
		}
		if(goalThighs == 0){
			tvThighs.setText(bodyThighs + " cm");
		}
		else{
			tvThighs.setText(bodyThighs + "/" + goalThighs + " cm");
		}
		if(goalCalves == 0){
			tvCalves.setText(bodyCalves + " cm");
		}
		else{
			tvCalves.setText(bodyCalves + "/" + goalCalves + " cm");
		}
		
	}
	
	public static UserInformation newInstance(String string) {
		// TODO Auto-generated method stub
		UserInformation f = new UserInformation();
		Bundle b = new Bundle();
		b.putString("msg", string);
		f.setArguments(b);

		return f;
	}
}
