package com.fitnessgear.adapter;

import java.util.ArrayList;
import java.util.Locale;

import com.fitnessgear.R;
import com.fitnessgear.adapter.ListExercisesAdapter.ViewHolder;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.model.PlanItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListPlansAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<PlanItem> listPlan;
	private ArrayList<PlanItem> filterPlan;
	
	public ListPlansAdapter(Context mContext,ArrayList<PlanItem> listPlan) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.listPlan = listPlan;
		this.filterPlan = new ArrayList<PlanItem>();
		this.filterPlan.addAll(listPlan);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listPlan.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listPlan.get(position);
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
            LayoutInflater mInflater = (LayoutInflater)
                    mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_plan_item, null);
            holder.planName = (TextView) convertView.findViewById(R.id.txtPlanName);
            holder.gender = (TextView) convertView.findViewById(R.id.txtGender);
            holder.mainGoal = (TextView) convertView.findViewById(R.id.txtMainGoal);
            
            holder.level = (TextView) convertView.findViewById(R.id.txtLevel);
            holder.totalWeek = (TextView) convertView.findViewById(R.id.txtTotalWeeks);
            holder.aveDay = (TextView) convertView.findViewById(R.id.txtAverageDay);
            holder.aveWorkoutTime = (TextView) convertView.findViewById(R.id.txtArverageTime);
            holder.totalTimeAWeek = (TextView) convertView.findViewById(R.id.txtTotalTimeAWeek);
            holder.totalCardio = (TextView) convertView.findViewById(R.id.txtTotalCardio);
            convertView.setTag(holder);
        }
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.planName.setText(""+listPlan.get(position).getPlanName());
		holder.gender.setText(""+listPlan.get(position).getGenderName());
		holder.mainGoal.setText(""+listPlan.get(position).getMainGoalName());
		holder.level.setText(""+listPlan.get(position).getFitnessLevelName());
		holder.totalWeek.setText(""+listPlan.get(position).getTotalWeeks());
		holder.aveDay.setText(""+listPlan.get(position).getAveDay());
		holder.aveWorkoutTime.setText(""+listPlan.get(position).getAveWorkoutTime());
		holder.totalTimeAWeek.setText(""+listPlan.get(position).getTotalTimeAWeek());
		holder.totalCardio.setText(""+listPlan.get(position).getTotalCardioTime());
		
		return convertView;
	}

	
	public static class ViewHolder {
		public TextView planName;
		public TextView gender;
		public TextView mainGoal;
		public TextView level;
		public TextView totalWeek;
		public TextView aveDay;
		public TextView aveWorkoutTime;
		public TextView totalTimeAWeek;
		public TextView totalCardio;
	}
	
	// Filter Class
    public void filter(String textFilter, int fitnessLevelID, int genderID, int mainGoalID) {
    	
    	//VIet Ham Filter vao day
    	//Khi Select tu bang Plan phai select tu bang Gender,FitnessLevel,Main_Goal de lay ra Name cua cac bang
    	//Giong voi String sql o duoi
    	//Can add vao bien listPlan sau do goi ham notifyDataSetChanged()
    	
    	textFilter = textFilter.toLowerCase(Locale.getDefault());
//    	int id1 = 0,id2 = 0,id3 = 0,id4;
//    	String sql = "Select * from Plan Where ";
//    	if (fitnessLevelID!=1) sql+= "Main goal = " + fitnessLevelID + " AND ";
//    	if (genderID!=1) sql+= "Gender = "+genderID + " AND ";
//    	if (mainGoalID!=1) sql+= "Level = "+mainGoalID + " AND ";

    	String sql = "Select * FROM Plan,Gender,FitnessLevel,Main_Goal Where MainGoal = MainGoalID AND Gender = GenderID AND FitnessLevel = FitnessLevelID AND PlanID > 1";
//    	String[] sql1 = sql.split(" ");
//    	for(int i = sql1.length; i > 0;i--){
//    		sql1[i]=sql1[i-1];
//    	}
    	
    	
        listPlan.clear();
        if (textFilter.length() == 0 && fitnessLevelID == 1 && genderID == 1 && mainGoalID == 1) {
        	listPlan.addAll(filterPlan);
        } 
        else 
        {
            for (PlanItem planItem : filterPlan) 
            {
            	if(planItem.getPlanName().toLowerCase(Locale.getDefault()).contains(textFilter)){
//            		if(planItem.getFitnessLevelID() == fitnessLevelID){
//            			if(planItem.getGenderID() == genderID){
//            				if(planItem.getMainGoalID() == mainGoalID){
//            					
//            				}
//            				if(planItem.getMainGoalID() != mainGoalID){
//            					
//            				}
//            			}
//            			if(planItem.getGenderID() != genderID){
//            				
//            			}
//            		}
//            		if(planItem.getFitnessLevelID() != fitnessLevelID){
//            			
//            		}
            		listPlan.add(planItem);
            	}
//            	if(!planItem.getPlanName().toLowerCase(Locale.getDefault()).contains(textFilter)){
//            		
//            	}
            }
        }
        notifyDataSetChanged();
    }
}

