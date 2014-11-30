package com.fitnessgear;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Fragment;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fitnessgear.adapter.ListFilterAdapter;
import com.fitnessgear.adapter.ListPlansAdapter;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.PlanItem;

public class FindAPlan extends Fragment {
	
	private ArrayList<PlanItem> myListPlan;
	private ListPlansAdapter adapter;
	private ArrayList<String> listFilterData;
	
	private EditText searchPlan;
	private ListView listFilter;
	private ListView listFullPlan;
	
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
        View rootView = inflater.inflate(R.layout.fragment_find_a_plan, container, false);
		
        searchPlan = (EditText) rootView.findViewById(R.id.searchPlan);
//        listFilter = (ListView) rootView.findViewById(R.id.listFilter);
        listFullPlan = (ListView) rootView.findViewById(R.id.listFullPlan);
        listFilter = (ListView) rootView.findViewById(R.id.listFilter);
        
        listFilterData = new ArrayList<String>();
        listFilterData.add("Level");
        listFilterData.add("Gender");
        listFilterData.add("Main Goal");
        
        ListFilterAdapter filterAdapter = new ListFilterAdapter(getActivity(),listFilterData);
        listFilter.setAdapter(filterAdapter);
        
        clearbutton = getResources()
				.getDrawable(R.drawable.text_field_clear_btn);
		searchbutton = getResources().getDrawable(R.drawable.search);
		
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.layoutFindAPlan);
		layout.setOnTouchListener(new OnTouchListener()
		{

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
	public void getData(){
		myListPlan = new ArrayList<PlanItem>();
		
		Cursor listPlanCursor = MainActivity.db.rawQuery("Select * FROM Plan", null);
		
		while(listPlanCursor.moveToNext()){
			myListPlan.add(new PlanItem(
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.PlanID),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.PlanName),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.MainGoal),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.Gender),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.FitnessLevel),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.CreatedBy),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.DateCreated),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.TotalWeeks),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.AveDay),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.AveWorkoutTime),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.TotalCardioTime),
					DatabaseUltility.GetColumnValue(listPlanCursor, DatabaseUltility.TotalTimeAWeek)));
		}
		
		adapter = new ListPlansAdapter(getActivity(), myListPlan);
		
		listFullPlan.setAdapter(adapter);
		
		searchPlan.setCompoundDrawablesWithIntrinsicBounds(searchbutton, null, null, null);
		searchPlan.setHint("Type something...");
		searchPlan.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
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
				adapter.filter(searchPlan.getText().toString()
						.toLowerCase(Locale.getDefault()));
			}
		});
		
		listFullPlan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				Intent exerciseDetailIntent = new Intent(getActivity(),
//						ExerciseDetail.class);
//				exerciseDetailIntent.putExtra("PlanID",
//						myListPlan.get(position).getPlanID());
//				// exerciseDetailIntent.putExtra("workoutID", workoutID);
//				searchPlan.clearFocus();
//				startActivity(exerciseDetailIntent);
			}
		});
	}
	
	//Tao ra anh nut clear va anh nut search
		public void handleClearButton() {
			if (searchPlan.getText().toString().equals("")) {
				// remove the clear button
				searchPlan.setCompoundDrawablesWithIntrinsicBounds(searchbutton, null, null, null);
			} else {
				// add clear button
				searchPlan.setCompoundDrawablesWithIntrinsicBounds(null, null, clearbutton, null);
			}
		}
		
		//An ban phim khi bam ra ban ngoai
		protected void hideKeyboard(View view)
		{
		    InputMethodManager in = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
		    in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
		
		private void onCreateDialog() {
			// TODO Auto-generated method stub

		}
}
