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
	
	public ListPlansAdapter(Context mContext,ArrayList<PlanItem> listExercises) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.listPlan = listExercises;
		this.filterPlan = new ArrayList<PlanItem>();
		this.filterPlan.addAll(listExercises);
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
		
		holder.planName.setText(listPlan.get(position).getPlanName());
		holder.gender.setText(listPlan.get(position).getGender());
		holder.mainGoal.setText(listPlan.get(position).getMainGoal());
		holder.level.setText(listPlan.get(position).getFitnessLevel());
		holder.totalWeek.setText(listPlan.get(position).getTotalWeeks());
		holder.aveDay.setText(listPlan.get(position).getAveDay());
		holder.aveWorkoutTime.setText(listPlan.get(position).getAveWorkoutTime());
		holder.totalTimeAWeek.setText(listPlan.get(position).getTotalTimeAWeek());
		holder.totalCardio.setText(listPlan.get(position).getTotalCardioTime());
		
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
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listPlan.clear();
        if (charText.length() == 0) {
        	listPlan.addAll(filterPlan);
        } 
        else 
        {
            for (PlanItem planItem : filterPlan) 
            {
                if (planItem.getPlanName().toLowerCase(Locale.getDefault()).contains(charText)) 
                {
                	listPlan.add(planItem);
                }
//                else if (exerciseItem.get().toLowerCase(Locale.getDefault()).contains(charText)) 
//                {
//                    listExercises.add(exerciseItem);
//                }
            }
        }
        notifyDataSetChanged();
    }
}

