package com.fitnessgear;

import java.util.ArrayList;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.fitnessgear.adapter.ListFilterAdapter;
import com.fitnessgear.adapter.ListPlansAdapter;
import com.fitnessgear.child.AddPlan;
import com.fitnessgear.child.PlanDetail;
import com.fitnessgear.child.TrackWorkout;
import com.fitnessgear.child.TrackWorkoutDialog;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.FilterItem;
import com.fitnessgear.model.FitnessLevelItem;
import com.fitnessgear.model.GenderItem;
import com.fitnessgear.model.MainGoalItem;
import com.fitnessgear.model.PlanItem;

public class FindAPlan extends Fragment {

	private ArrayList<PlanItem> myListPlan;
	private ListPlansAdapter adapter;
	private ArrayList<FilterItem> listFilterData;
	private ArrayList<FitnessLevelItem> listLevel;
	private ArrayList<GenderItem> listGender;
	private ArrayList<MainGoalItem> listMainGoal;
	
	private EditText searchPlan;
	private ListView listFilter;
	private ListView listFullPlan;
	
	private int fitnessLevelID = 1;
	private int genderID = 1;
	private int mainGoalID = 1;

	// The image we are going to use for the Clear button
	private Drawable clearbutton;
	private Drawable searchbutton;

	public FindAPlan() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_find_a_plan,
				container, false);
		setHasOptionsMenu(true);

		searchPlan = (EditText) rootView.findViewById(R.id.searchPlan);
		// listFilter = (ListView) rootView.findViewById(R.id.listFilter);
		listFullPlan = (ListView) rootView.findViewById(R.id.listFullPlan);
		listFilter = (ListView) rootView.findViewById(R.id.listFilterPlan);

		

		clearbutton = getResources().getDrawable(
				R.drawable.text_field_clear_btn);
		searchbutton = getResources().getDrawable(R.drawable.search);

		LinearLayout layout = (LinearLayout) rootView
				.findViewById(R.id.layoutFindAPlan);
		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				searchPlan.clearFocus();
				handleClearButton();
				hideKeyboard(view);
				return false;
			}
		});

		getData();
		return rootView;
	}

	public void getData() {
		//Create List Filter
		listFilterData = new ArrayList<FilterItem>();
		listFilterData.add(new FilterItem("Level","All Levels"));
		listFilterData.add(new FilterItem("Gender","All Genders"));
		listFilterData.add(new FilterItem("Main Goal","All Goals"));

		final ListFilterAdapter filterAdapter = new ListFilterAdapter(getActivity(),
				listFilterData);
		listFilter.setAdapter(filterAdapter);
		
		final Cursor fitnessLevel = MainActivity.db.rawQuery("Select * FROM FitnessLevel", null);
		final Cursor gender = MainActivity.db.rawQuery("Select * FROM Gender", null);
		final Cursor mainGoal = MainActivity.db.rawQuery("Select * FROM Main_Goal", null);
		listLevel = new ArrayList<FitnessLevelItem>();
		listGender = new ArrayList<GenderItem>();
		listMainGoal = new ArrayList<MainGoalItem>();
		while(fitnessLevel.moveToNext()){
			listLevel.add(new FitnessLevelItem(DatabaseUltility.GetIntColumnValue(fitnessLevel, DatabaseUltility.FitnessLevelID), DatabaseUltility.GetColumnValue(fitnessLevel, DatabaseUltility.FitnessLevelName)));
		}
		while(gender.moveToNext()){
			listGender.add(new GenderItem(DatabaseUltility.GetIntColumnValue(gender, DatabaseUltility.GenderID), DatabaseUltility.GetColumnValue(gender, DatabaseUltility.GenderName)));
		}
		while(mainGoal.moveToNext()){
			listMainGoal.add(new MainGoalItem(DatabaseUltility.GetIntColumnValue(mainGoal, DatabaseUltility.MainGoalID), DatabaseUltility.GetColumnValue(mainGoal, DatabaseUltility.MainGoalName)));
		}
		
		final ArrayAdapter<FitnessLevelItem> levelAdapter = new ArrayAdapter<FitnessLevelItem>(getActivity(), android.R.layout.simple_list_item_1,listLevel);
		final ArrayAdapter<GenderItem> genderAdapter = new ArrayAdapter<GenderItem>(getActivity(), android.R.layout.simple_list_item_1, listGender);
		final ArrayAdapter<MainGoalItem> mainGoalAdapter = new ArrayAdapter<MainGoalItem>(getActivity(), android.R.layout.simple_list_item_1, listMainGoal);
		
		listFilter.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					new AlertDialog.Builder(getActivity())
				    .setTitle("Level")
				    .setSingleChoiceItems(levelAdapter, 0, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							fitnessLevelID = listLevel.get(((AlertDialog)dialog).getListView().getCheckedItemPosition()).getFitnessLevelID();
							listFilterData.get(0).setValueFilter(listLevel.get(((AlertDialog)dialog).getListView().getCheckedItemPosition()).getFitnessLevelName());
							filterAdapter.notifyDataSetChanged();
							adapter.filter(
									searchPlan.getText().toString(),
									fitnessLevelID,
									genderID,
									mainGoalID);
						}
					})
				     .show();
					break;
				case 1:
					new AlertDialog.Builder(getActivity())
				    .setTitle("Gender")
				    .setSingleChoiceItems(genderAdapter, 0, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							genderID = listGender.get(((AlertDialog)dialog).getListView().getCheckedItemPosition()).getGenderID();
							listFilterData.get(1).setValueFilter(listGender.get(((AlertDialog)dialog).getListView().getCheckedItemPosition()).toString());
							filterAdapter.notifyDataSetChanged();
							adapter.filter(
									searchPlan.getText().toString(),
									fitnessLevelID,
									genderID,
									mainGoalID);
							Toast.makeText(getActivity(), listFilterData.get(1).getValueFilter().toString().toLowerCase(Locale.getDefault()), 0).show();
						}
					})
				     .show();
					break;
				case 2:
					new AlertDialog.Builder(getActivity())
				    .setTitle("Main Goal")
				    .setSingleChoiceItems(mainGoalAdapter, 0, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							mainGoalID = listMainGoal.get(((AlertDialog)dialog).getListView().getCheckedItemPosition()).getMainGoalID();
							listFilterData.get(2).setValueFilter(listMainGoal.get(((AlertDialog)dialog).getListView().getCheckedItemPosition()).toString());
							filterAdapter.notifyDataSetChanged();
							adapter.filter(
									searchPlan.getText().toString(),
									fitnessLevelID,
									genderID,
									mainGoalID);
							Toast.makeText(getActivity(), listFilterData.get(2).getValueFilter().toString().toLowerCase(Locale.getDefault()), 0).show();
						}
					})
				     .show();
					break;

				default:
					break;
				}				
			}
		});
		//Create List Plan
		myListPlan = new ArrayList<PlanItem>();
		Cursor listPlanCursor = MainActivity.db.rawQuery(
				"Select * " +
				"FROM Plan,Gender,FitnessLevel,Main_Goal " +
				"Where MainGoal = MainGoalID " +
				"AND Gender = GenderID " +
				"AND FitnessLevel = FitnessLevelID " +
				"AND PlanID > 1",null);

		while (listPlanCursor.moveToNext()) {
			myListPlan.add(new PlanItem(
					DatabaseUltility.GetIntColumnValue(listPlanCursor,DatabaseUltility.PlanID),
					DatabaseUltility.GetColumnValue(listPlanCursor,DatabaseUltility.PlanName),
					DatabaseUltility.GetIntColumnValue(listPlanCursor,DatabaseUltility.MainGoalID),
					DatabaseUltility.GetColumnValue(listPlanCursor,DatabaseUltility.MainGoalName),
					DatabaseUltility.GetIntColumnValue(listPlanCursor,DatabaseUltility.GenderID),
					DatabaseUltility.GetColumnValue(listPlanCursor,DatabaseUltility.GenderName),
					DatabaseUltility.GetIntColumnValue(listPlanCursor,DatabaseUltility.FitnessLevelID),
					DatabaseUltility.GetColumnValue(listPlanCursor,DatabaseUltility.FitnessLevelName),
					DatabaseUltility.GetColumnValue(listPlanCursor,DatabaseUltility.CreatedBy),
					DatabaseUltility.GetColumnValue(listPlanCursor,DatabaseUltility.DateCreated),
					DatabaseUltility.GetIntColumnValue(listPlanCursor,DatabaseUltility.TotalWeeks),
					DatabaseUltility.GetFloatColumnValue(listPlanCursor,DatabaseUltility.AveDay),
					DatabaseUltility.GetFloatColumnValue(listPlanCursor,DatabaseUltility.AveWorkoutTime),
					DatabaseUltility.GetIntColumnValue(listPlanCursor,DatabaseUltility.TotalCardioTime),
					DatabaseUltility.GetIntColumnValue(listPlanCursor,DatabaseUltility.TotalTimeAWeek)));
		}

		adapter = new ListPlansAdapter(getActivity(), myListPlan);

		listFullPlan.setAdapter(adapter);

		searchPlan.setCompoundDrawablesWithIntrinsicBounds(searchbutton, null,
				null, null);
		searchPlan.setHint("Type Plan Name");
		searchPlan.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				 handleClearButton();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				adapter.filter(
						searchPlan.getText().toString(),
						fitnessLevelID,
						genderID,
						mainGoalID);
			}
		});

		listFullPlan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				 Intent planDetailIntent = new Intent(getActivity(),
						 PlanDetail.class);
				 planDetailIntent.putExtra("PlanID",
				 myListPlan.get(position).getPlanID());
				 // exerciseDetailIntent.putExtra("workoutID", workoutID);
				 searchPlan.clearFocus();
				 startActivity(planDetailIntent);
			}
		});
	}

	// Tao ra anh nut clear va anh nut search
	public void handleClearButton() {
		if (searchPlan.getText().toString().equals("")) {
			// remove the clear button
			searchPlan.setCompoundDrawablesWithIntrinsicBounds(searchbutton,
					null, null, null);
		} else {
			// add clear button
			searchPlan.setCompoundDrawablesWithIntrinsicBounds(null, null,
					null, null);
		}
	}

	// An ban phim khi bam ra ban ngoai
	protected void hideKeyboard(View view) {
		InputMethodManager in = (InputMethodManager) getActivity()
				.getSystemService(getActivity().INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.find_a_plan, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.add_Plan) {
			Intent intent = new Intent(getActivity(),AddPlan.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
