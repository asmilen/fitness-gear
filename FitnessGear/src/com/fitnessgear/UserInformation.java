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
		} else {
//			progressWeight.setProgress(0);
//			progressWeight.setSecondaryProgress(0);
			tvWeight.setText(bodyWeight + "/" + goalWeight + " kg");
			if(bodyWeight >= goalWeight){
				float progress = (goalWeight / bodyWeight) * 100;
				progressWeight.setSecondaryProgress(100);
				progressWeight.setProgress((int) progress);
			}
			if(bodyWeight < goalWeight){
				float progress = (goalWeight / bodyWeight) * 100;
				progressWeight.setProgress(100);
				progressWeight.setSecondaryProgress((int) progress);
			}
		}
		if (goalBodyFat == 0) {
			tvBodyFat.setText(bodyBodyFat + " %");
		} else {
			tvBodyFat.setText(bodyBodyFat + "/" + goalBodyFat + " %");
			float progress = goalBodyFat / (bodyBodyFat / 100);
			if (progress >= 0 && progress <= 100) {
				progressBodyFat.setProgress((int) progress);
			}
			if (progress > 100) {
				progressBodyFat.setProgress(100);
				progressBodyFat.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalHeight == 0) {
			tvHeight.setText(bodyHeight + " cm");
		} else {
			tvHeight.setText(bodyHeight + "/" + goalHeight + " cm");
			float progress = goalHeight / (bodyHeight / 100);
			if (progress >= 0 && progress <= 100) {
				progressHeight.setProgress((int) progress);
			}
			if (progress > 100) {
				progressHeight.setProgress(100);
				progressHeight.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalChest == 0) {
			tvChest.setText(bodyChest + " cm");
		} else {
			tvChest.setText(bodyChest + "/" + goalChest + " cm");
			float progress = goalHeight / (bodyHeight / 100);
			if (progress >= 0 && progress <= 100) {
				progressHeight.setProgress((int) progress);
			}
			if (progress > 100) {
				progressHeight.setProgress(100);
				progressHeight.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalWaist == 0) {
			tvWaist.setText(bodyWaist + " cm");
		} else {
			tvWaist.setText(bodyWaist + "/" + goalWaist + " cm");
			float progress = goalWaist / (bodyWaist / 100);
			if (progress >= 0 && progress <= 100) {
				progressWaist.setProgress((int) progress);
			}
			if (progress > 100) {
				progressWaist.setProgress(100);
				progressWaist.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalArms == 0) {
			tvArms.setText(bodyArms + " cm");
		} else {
			tvArms.setText(bodyArms + "/" + goalArms + " cm");
			float progress = goalArms / (bodyArms / 100);
			if (progress >= 0 && progress <= 100) {
				progressArms.setProgress((int) progress);
			}
			if (progress > 100) {
				progressArms.setProgress(100);
				progressArms.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalShoulders == 0) {
			tvShoulders.setText(bodyShoulders + " cm");
		} else {
			tvShoulders.setText(bodyShoulders + "/" + goalShoulders + " cm");
			float progress = goalShoulders / (bodyShoulders / 100);
			if (progress >= 0 && progress <= 100) {
				progressShoulders.setProgress((int) progress);
			}
			if (progress > 100) {
				progressShoulders.setProgress(100);
				progressShoulders.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalForearms == 0) {
			tvForearms.setText(bodyForearms + " cm");
		} else {
			tvForearms.setText(bodyForearms + "/" + goalForearms + " cm");
			float progress = goalForearms / (bodyForearms / 100);
			if (progress >= 0 && progress <= 100) {
				progressForearms.setProgress((int) progress);
			}
			if (progress > 100) {
				progressForearms.setProgress(100);
				progressForearms.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalNeck == 0) {
			tvNeck.setText(bodyNeck + " cm");
		} else {
			tvNeck.setText(bodyNeck + "/" + goalNeck + " cm");
			float progress = goalNeck / (bodyNeck / 100);
			if (progress >= 0 && progress <= 100) {
				progressNeck.setProgress((int) progress);
			}
			if (progress > 100) {
				progressNeck.setProgress(100);
				progressNeck.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalHips == 0) {
			tvHips.setText(bodyHips + " cm");
		} else {
			tvHips.setText(bodyHips + "/" + goalHips + " cm");
			float progress = goalHips / (bodyHips / 100);
			if (progress >= 0 && progress <= 100) {
				progressHips.setProgress((int) progress);
			}
			if (progress > 100) {
				progressHips.setProgress(100);
				progressHips.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalThighs == 0) {
			tvThighs.setText(bodyThighs + " cm");
		} else {
			tvThighs.setText(bodyThighs + "/" + goalThighs + " cm");
			float progress = goalThighs / (bodyThighs / 100);
			if (progress >= 0 && progress <= 100) {
				progressThighs.setProgress((int) progress);
			}
			if (progress > 100) {
				progressThighs.setProgress(100);
				progressThighs.setSecondaryProgress((int) progress - 100);
			}
		}
		if (goalCalves == 0) {
			tvCalves.setText(bodyCalves + " cm");
		} else {
			tvCalves.setText(bodyCalves + "/" + goalCalves + " cm");
			float progress = goalCalves / (bodyCalves / 100);
			if (progress >= 0 && progress <= 100) {
				progressCalves.setProgress((int) progress);
			}
			if (progress > 100) {
				progressCalves.setProgress(100);
				progressCalves.setSecondaryProgress((int) progress - 100);
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
