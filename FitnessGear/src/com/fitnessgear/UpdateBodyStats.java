package com.fitnessgear;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBodyStats extends Fragment {

	private Button btnSaveUpdateStats;
	
	private EditText etWeight;
	private EditText etBodyFat;
	private EditText etHeight;
	private EditText etChest;
	private EditText etWaist;
	private EditText etArms;
	private EditText etShoulders;
	private EditText etForearms;
	private EditText etNeck;
	private EditText etHips;
	private EditText etThighs;
	private EditText etClaves;
	
	private String UserID = "1";
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_update_stats,
				container, false);
		
		btnSaveUpdateStats = (Button) rootView.findViewById(R.id.btnSaveUpdateStats);

		etWeight = (EditText) rootView.findViewById(R.id.etWeightBody);
		etBodyFat = (EditText) rootView.findViewById(R.id.etBodyFat);
		etHeight = (EditText) rootView.findViewById(R.id.etHeightBody);
		etChest = (EditText) rootView.findViewById(R.id.etChestBody);
		etWaist = (EditText) rootView.findViewById(R.id.etWaistBody);
		etArms = (EditText) rootView.findViewById(R.id.etArmsBody);
		etShoulders = (EditText) rootView.findViewById(R.id.etShouldersBody);
		etForearms = (EditText) rootView.findViewById(R.id.etForearmsBody);
		etNeck = (EditText) rootView.findViewById(R.id.etNeckBody);
		etHips = (EditText) rootView.findViewById(R.id.etHipsBody);
		etThighs = (EditText) rootView.findViewById(R.id.etThighsBody);
		etClaves = (EditText) rootView.findViewById(R.id.etCalvesBody);
		
		btnSaveUpdateStats.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				float weight = Float.parseFloat(etWeight.getText().toString());
				float bodyFat = Float.parseFloat(etBodyFat.getText().toString());
				float height = Float.parseFloat(etHeight.getText().toString());
				float chest = Float.parseFloat(etChest.getText().toString());
				float waist = Float.parseFloat(etWaist.getText().toString());
				float arms = Float.parseFloat(etArms.getText().toString());
				float shoulders = Float.parseFloat(etShoulders.getText().toString());
				float forearms = Float.parseFloat(etForearms.getText().toString());
				float neck = Float.parseFloat(etNeck.getText().toString());
				float hips = Float.parseFloat(etHips.getText().toString());
				float thighs = Float.parseFloat(etThighs.getText().toString());
				float calves = Float.parseFloat(etClaves.getText().toString());
				if(weight>0 && weight <200
						&& bodyFat > 0 && bodyFat < 100
						&& height > 0 && height < 300
						&& chest > 0 && chest < 200
						&& waist > 0 && waist < 200
						&& arms > 0 && arms < 200
						&& shoulders > 0 && shoulders < 200
						&& forearms > 0 && forearms < 200
						&& neck > 0 && neck < 200
						&& hips > 0 && hips < 200
						&& thighs > 0 && thighs < 200
						&& calves > 0 && calves < 200){
					try{
						
					
					MainActivity.db = MainActivity.dbHelper.getWritableDatabase();
					ContentValues userContent = new ContentValues();
					userContent.put("Weight", weight);
					userContent.put("BodyFat", bodyFat);
					userContent.put("Height", height);
					userContent.put("Waist", waist);
					userContent.put("Chest", chest);
					userContent.put("Arms", arms);
					userContent.put("Forearms", forearms);
					userContent.put("Shoulders", shoulders);
					userContent.put("Hips", hips);
					userContent.put("Thighs", thighs);
					userContent.put("Calves", calves);
					userContent.put("Neck", neck);
					int updateStats = MainActivity.db.update("User", userContent, "UserID=?",new String[]{UserID});
					if(updateStats>0){
						Toast.makeText(getActivity(), "Update Successfull", Toast.LENGTH_LONG).show();
					}
					}catch(Exception ex){
						Toast.makeText(getActivity(), "" + ex, Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		
		return rootView;
	}

	public static UpdateBodyStats newInstance(String string) {
		// TODO Auto-generated method stub
		UpdateBodyStats f = new UpdateBodyStats();
		Bundle b = new Bundle();
		b.putString("msg", string);
		f.setArguments(b);

		return f;
	}
}
