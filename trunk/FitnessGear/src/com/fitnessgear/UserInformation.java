package com.fitnessgear;

import com.fitnessgear.database.DatabaseUltility;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
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

	private static ProgressBar progressWeight;
	private static ProgressBar progressBodyFat;
	private static ProgressBar progressHeight;
	private static ProgressBar progressChest;
	private static ProgressBar progressWaist;
	private static ProgressBar progressArms;
	private static ProgressBar progressShoulders;
	private static ProgressBar progressForearms;
	private static ProgressBar progressNeck;
	private static ProgressBar progressHips;
	private static ProgressBar progressThighs;
	private static ProgressBar progressCalves;
	
	private static Drawable draw1;
	private static Drawable draw2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_user_information,
				container, false);

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

		progressWeight = (ProgressBar) rootView
				.findViewById(R.id.progressWeight);
		progressBodyFat = (ProgressBar) rootView
				.findViewById(R.id.progressBodyFat);
		progressHeight = (ProgressBar) rootView
				.findViewById(R.id.progressHeight);
		progressChest = (ProgressBar) rootView.findViewById(R.id.progressChest);
		progressWaist = (ProgressBar) rootView.findViewById(R.id.progressWaist);
		progressArms = (ProgressBar) rootView.findViewById(R.id.progressArms);
		progressShoulders = (ProgressBar) rootView
				.findViewById(R.id.progressShoulders);
		progressForearms = (ProgressBar) rootView
				.findViewById(R.id.progressForearms);
		progressNeck = (ProgressBar) rootView.findViewById(R.id.progressNeck);
		progressHips = (ProgressBar) rootView.findViewById(R.id.progressHips);
		progressThighs = (ProgressBar) rootView
				.findViewById(R.id.progressThighs);
		progressCalves = (ProgressBar) rootView
				.findViewById(R.id.progressCalves);
		
		draw1 = getResources().getDrawable(R.drawable.custome_progress_bar);
		draw2 = getResources().getDrawable(R.drawable.custom_progress_bar_2);

		getData();
		return rootView;
	}

	public static void getData() {

		Cursor bodyStats = MainActivity.db.rawQuery(
				"SELECT * FROM User WHERE UserID = 1", null);
		while (bodyStats.moveToNext()) {
			bodyWeight = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Weight);
			bodyBodyFat = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.BodyFat);
			bodyHeight = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Height);
			bodyChest = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Chest);
			bodyWaist = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Waist);
			bodyArms = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Arms);
			bodyShoulders = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Shoulders);
			bodyForearms = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Forearms);
			bodyNeck = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Neck);
			bodyHips = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Hips);
			bodyThighs = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Thighs);
			bodyCalves = DatabaseUltility.GetFloatColumnValue(bodyStats,
					DatabaseUltility.Calves);

		}
		Cursor bodyGoals = MainActivity.db.rawQuery(
				"SELECT * FROM User WHERE UserID = 2", null);
		while (bodyGoals.moveToNext()) {
			goalWeight = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Weight);
			goalBodyFat = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.BodyFat);
			goalHeight = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Height);
			goalChest = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Chest);
			goalWaist = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Waist);
			goalArms = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Arms);
			goalShoulders = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Shoulders);
			goalForearms = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Forearms);
			goalNeck = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Neck);
			goalHips = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Hips);
			goalThighs = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Thighs);
			goalCalves = DatabaseUltility.GetFloatColumnValue(bodyGoals,
					DatabaseUltility.Calves);

		}

		if (goalWeight == 0) {
			tvWeight.setText(bodyWeight + " kg");
			progressWeight.setProgress(0);
			progressWeight.setSecondaryProgress(0);
		} else {
			progressWeight.setProgress(0);
			progressWeight.setSecondaryProgress(0);
			tvWeight.setText(bodyWeight + "/" + goalWeight + " kg");
			if(bodyWeight >= goalWeight){
				float progressForWeight = (goalWeight / bodyWeight) * 100;
				progressWeight.setProgressDrawable(draw2);
				progressWeight.setProgress(100);
				progressWeight.setSecondaryProgress((int) progressForWeight);
			}
			if(bodyWeight < goalWeight){
				float progressForWeight = (bodyWeight / goalWeight) * 100;
				progressWeight.setProgressDrawable(draw1);
				progressWeight.setProgress(100);
				progressWeight.setSecondaryProgress((int) progressForWeight);
			}
		}
		if (goalBodyFat == 0) {
			tvBodyFat.setText(bodyBodyFat + " %");
			progressBodyFat.setProgress(0);
			progressBodyFat.setSecondaryProgress(0);
		} else {
			progressBodyFat.setProgress(0);
			progressBodyFat.setSecondaryProgress(0);
			tvBodyFat.setText(bodyBodyFat + "/" + goalBodyFat + " %");
			if(bodyBodyFat >= goalBodyFat){
				float progressForBodyFat = (goalBodyFat / bodyBodyFat) * 100;
				progressBodyFat.setProgressDrawable(draw2);
				progressBodyFat.setProgress(100);
				progressBodyFat.setSecondaryProgress((int) progressForBodyFat);
			}
			if(bodyBodyFat < goalBodyFat){
				float progress = (bodyBodyFat / goalBodyFat) * 100;
				progressBodyFat.setProgressDrawable(draw1);
				progressBodyFat.setProgress(100);
				progressBodyFat.setSecondaryProgress((int) progress);
			}
		}
		if (goalHeight == 0) {
			tvHeight.setText(bodyHeight + " cm");
			progressHeight.setProgress(0);
			progressHeight.setSecondaryProgress(0);
		} else {
			progressHeight.setProgress(0);
			progressHeight.setSecondaryProgress(0);
			tvHeight.setText(bodyHeight + "/" + goalHeight + " cm");
			if(bodyHeight >= goalHeight){
				float progress = (goalHeight / bodyHeight) * 100;
				progressHeight.setProgressDrawable(draw2);
				progressHeight.setProgress(100);
				progressHeight.setSecondaryProgress((int) progress);
			}
			if(bodyHeight < goalHeight){
				float progress = (bodyHeight / goalHeight) * 100;
				progressHeight.setProgressDrawable(draw1);
				progressHeight.setProgress(100);
				progressHeight.setSecondaryProgress((int) progress);
			}
		}
		if (goalChest == 0) {
			tvChest.setText(bodyChest + " cm");
			progressChest.setProgress(0);
			progressChest.setSecondaryProgress(0);
		} else {
			tvChest.setText(bodyChest + "/" + goalChest + " cm");
			if(bodyChest >= goalChest){
				float progress = (goalChest / bodyChest) * 100;
				progressChest.setProgressDrawable(draw2);
				progressChest.setProgress(100);
				progressChest.setSecondaryProgress((int) progress);
			}
			if(bodyHeight < goalChest){
				float progress = (bodyChest / goalChest) * 100;
				progressChest.setProgressDrawable(draw1);
				progressChest.setProgress(100);
				progressChest.setSecondaryProgress((int) progress);
			}
		}
		if (goalWaist == 0) {
			tvWaist.setText(bodyWaist + " cm");
			progressWaist.setProgress(0);
			progressWaist.setSecondaryProgress(0);
		} else {
			tvWaist.setText(bodyWaist + "/" + goalWaist + " cm");
			if(bodyWaist >= goalWaist){
				float progress = (goalWaist / bodyWaist) * 100;
				progressWaist.setProgressDrawable(draw2);
				progressWaist.setProgress(100);
				progressWaist.setSecondaryProgress((int) progress);
			}
			if(bodyWaist < goalWaist){
				float progress = (bodyWaist / goalWaist) * 100;
				progressWaist.setProgressDrawable(draw1);
				progressWaist.setProgress(100);
				progressWaist.setSecondaryProgress((int) progress);
			}
		}
		if (goalArms == 0) {
			tvArms.setText(bodyArms + " cm");
			progressArms.setProgress(0);
			progressArms.setSecondaryProgress(0);
		} else {
			tvArms.setText(bodyArms + "/" + goalArms + " cm");
			if(bodyArms >= goalArms){
				float progress = (goalArms / bodyArms) * 100;
				progressArms.setProgressDrawable(draw2);
				progressArms.setProgress(100);
				progressArms.setSecondaryProgress((int) progress);
			}
			if(bodyArms < goalArms){
				float progress = (bodyArms / goalArms) * 100;
				progressArms.setProgressDrawable(draw1);
				progressArms.setProgress(100);
				progressArms.setSecondaryProgress((int) progress);
			}
		}
		if (goalShoulders == 0) {
			tvShoulders.setText(bodyShoulders + " cm");
			progressShoulders.setProgress(0);
			progressShoulders.setSecondaryProgress(0);
		} else {
			tvShoulders.setText(bodyShoulders + "/" + goalShoulders + " cm");
			if(bodyShoulders >= goalShoulders){
				float progress = (goalShoulders / bodyShoulders) * 100;
				progressShoulders.setProgressDrawable(draw2);
				progressShoulders.setProgress(100);
				progressShoulders.setSecondaryProgress((int) progress);
			}
			if(bodyShoulders < goalShoulders){
				float progress = (bodyShoulders / goalShoulders) * 100;
				progressShoulders.setProgressDrawable(draw1);
				progressShoulders.setProgress(100);
				progressShoulders.setSecondaryProgress((int) progress);
			}
		}
		if (goalForearms == 0) {
			tvForearms.setText(bodyForearms + " cm");
			progressForearms.setProgress(0);
			progressForearms.setSecondaryProgress(0);
		} else {
			tvForearms.setText(bodyForearms + "/" + goalForearms + " cm");
			if(bodyForearms >= goalForearms){
				float progress = (goalForearms / bodyForearms) * 100;
				progressForearms.setProgressDrawable(draw2);
				progressForearms.setProgress(100);
				progressForearms.setSecondaryProgress((int) progress);
			}
			if(bodyForearms < goalForearms){
				float progress = (bodyForearms / goalForearms) * 100;
				progressForearms.setProgressDrawable(draw1);
				progressForearms.setProgress(100);
				progressForearms.setSecondaryProgress((int) progress);
			}
		}
		if (goalNeck == 0) {
			tvNeck.setText(bodyNeck + " cm");
			progressNeck.setProgress(0);
			progressNeck.setSecondaryProgress(0);
		} else {
			tvNeck.setText(bodyNeck + "/" + goalNeck + " cm");
			if(bodyNeck >= goalNeck){
				float progress = (goalNeck / bodyNeck) * 100;
				progressNeck.setProgressDrawable(draw2);
				progressNeck.setProgress(100);
				progressNeck.setSecondaryProgress((int) progress);
			}
			if(bodyHeight < goalNeck){
				float progress = (bodyNeck / goalNeck) * 100;
				progressNeck.setProgressDrawable(draw1);
				progressNeck.setProgress(100);
				progressNeck.setSecondaryProgress((int) progress);
			}
		}
		if (goalHips == 0) {
			tvHips.setText(bodyHips + " cm");
			progressHips.setProgress(0);
			progressHips.setSecondaryProgress(0);
		} else {
			tvHips.setText(bodyHips + "/" + goalHips + " cm");
			if(bodyHips >= goalHips){
				float progress = (goalHips / bodyHips) * 100;
				progressHips.setProgressDrawable(draw2);
				progressHips.setProgress(100);
				progressHips.setSecondaryProgress((int) progress);
			}
			if(bodyHips < goalHeight){
				float progress = (bodyHips / goalHips) * 100;
				progressHips.setProgressDrawable(draw1);
				progressHips.setProgress(100);
				progressHips.setSecondaryProgress((int) progress);
			}
		}
		if (goalThighs == 0) {
			tvThighs.setText(bodyThighs + " cm");
			progressThighs.setProgress(0);
			progressThighs.setSecondaryProgress(0);
		} else {
			tvThighs.setText(bodyThighs + "/" + goalThighs + " cm");
			if(bodyThighs >= goalThighs){
				float progress = (goalThighs / bodyThighs) * 100;
				progressThighs.setProgressDrawable(draw2);
				progressThighs.setProgress(100);
				progressThighs.setSecondaryProgress((int) progress);
			}
			if(bodyThighs < goalThighs){
				float progress = (bodyThighs / goalThighs) * 100;
				progressThighs.setProgressDrawable(draw1);
				progressThighs.setProgress(100);
				progressThighs.setSecondaryProgress((int) progress);
			}
		}
		if (goalCalves == 0) {
			tvCalves.setText(bodyCalves + " cm");
			progressCalves.setProgress(0);
			progressCalves.setSecondaryProgress(0);
		} else {
			tvCalves.setText(bodyCalves + "/" + goalCalves + " cm");
			if(bodyCalves >= goalCalves){
				float progress = (goalCalves / bodyCalves) * 100;
				progressCalves.setProgressDrawable(draw2);
				progressCalves.setProgress(100);
				progressCalves.setSecondaryProgress((int) progress);
			}
			if(bodyCalves < goalCalves){
				float progress = (bodyCalves / goalCalves) * 100;
				progressCalves.setProgressDrawable(draw1);
				progressCalves.setProgress(100);
				progressCalves.setSecondaryProgress((int) progress);
			}
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
