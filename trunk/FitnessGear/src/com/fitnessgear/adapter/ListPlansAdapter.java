package com.fitnessgear.adapter;

import java.util.ArrayList;
import java.util.Locale;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.adapter.ListExercisesAdapter.ViewHolder;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.ExercisesItem;
import com.fitnessgear.model.PlanItem;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
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

	public ListPlansAdapter(Context mContext, ArrayList<PlanItem> listPlan) {
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
			LayoutInflater mInflater = (LayoutInflater) mContext
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.list_plan_item, null);
			holder.planName = (TextView) convertView
					.findViewById(R.id.txtPlanName);
			holder.gender = (TextView) convertView.findViewById(R.id.txtGender);
			holder.mainGoal = (TextView) convertView
					.findViewById(R.id.txtMainGoal);

			holder.level = (TextView) convertView.findViewById(R.id.txtLevel);
			holder.totalWeek = (TextView) convertView
					.findViewById(R.id.txtTotalWeeks);
			holder.aveDay = (TextView) convertView
					.findViewById(R.id.txtAverageDay);
			holder.aveWorkoutTime = (TextView) convertView
					.findViewById(R.id.txtArverageTime);
			holder.totalTimeAWeek = (TextView) convertView
					.findViewById(R.id.txtTotalTimeAWeek);
			holder.totalCardio = (TextView) convertView
					.findViewById(R.id.txtTotalCardio);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.planName.setText("" + listPlan.get(position).getPlanName());
		holder.gender.setText("" + listPlan.get(position).getGenderName());
		holder.mainGoal.setText("" + listPlan.get(position).getMainGoalName());
		holder.level.setText("" + listPlan.get(position).getFitnessLevelName());
		holder.totalWeek.setText("" + listPlan.get(position).getTotalWeeks());
		holder.aveDay.setText("" + listPlan.get(position).getAveDay());
		holder.aveWorkoutTime.setText(""
				+ listPlan.get(position).getAveWorkoutTime());
		holder.totalTimeAWeek.setText(""
				+ listPlan.get(position).getTotalTimeAWeek());
		holder.totalCardio.setText(""
				+ listPlan.get(position).getTotalCardioTime());

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
	public void filter(String textFilter, int fitnessLevelID, int genderID,
			int mainGoalID) {

		// VIet Ham Filter vao day
		// Khi Select tu bang Plan phai select tu bang
		// Gender,FitnessLevel,Main_Goal de lay ra Name cua cac bang
		// Giong voi String sql o duoi
		// Can add vao bien listPlan sau do goi ham notifyDataSetChanged()

		textFilter = textFilter.toLowerCase(Locale.getDefault());

		// BUILD QUERY WITH FILTER
		String sql = "Select * from Plan Where PlanID>1 AND ";
		if (mainGoalID != 1)
			sql += "Maingoal = " + mainGoalID + " AND ";
		if (genderID != 1)
			sql += "Gender = " + genderID + " AND ";
		if (fitnessLevelID != 1)
			sql += "FitnessLevel = " + fitnessLevelID + " AND ";
		if(textFilter != ""){
			sql += "PlanName LIKE '%" + textFilter + "%' AND";
		}

		// Delete last AND
		String[] sql1 = sql.split(" ");
		StringBuilder builder = new StringBuilder();
		String delimiter = " ";
		for (int i = 0; i < sql1.length - 1; i++) {
			builder.append(sql1[i]);
			builder.append(delimiter);
		}
		sql = builder.toString();

		listPlan.clear();

		// Exec query and add to listPlan
		Cursor c = MainActivity.db.rawQuery(sql, null);
		while (c.moveToNext()) {
			String mainGoalName = getGoalName(DatabaseUltility.GetColumnValue(c, DatabaseUltility.MainGoal));
			String genderName = getGenderName(DatabaseUltility.GetColumnValue(c, DatabaseUltility.Gender));
			String fitnessLevelName = getFitnessLevelName(DatabaseUltility.GetColumnValue(c, DatabaseUltility.FitnessLevel));
			listPlan.add(new PlanItem(DatabaseUltility.GetIntColumnValue(c,
					DatabaseUltility.PlanID), DatabaseUltility.GetColumnValue(
					c, DatabaseUltility.PlanName), mainGoalID, mainGoalName,
					genderID, genderName, fitnessLevelID, fitnessLevelName,
					DatabaseUltility.GetColumnValue(c,
							DatabaseUltility.CreatedBy), DatabaseUltility
							.GetColumnValue(c, DatabaseUltility.DateCreated),
					DatabaseUltility.GetIntColumnValue(c,
							DatabaseUltility.TotalWeeks), DatabaseUltility
							.GetFloatColumnValue(c, DatabaseUltility.AveDay),
					DatabaseUltility.GetFloatColumnValue(c,
							DatabaseUltility.AveWorkoutTime), DatabaseUltility
							.GetIntColumnValue(c,
									DatabaseUltility.TotalCardioTime),
					DatabaseUltility.GetIntColumnValue(c,
							DatabaseUltility.TotalTimeAWeek)));

		}
		notifyDataSetChanged();
	}

	private String getGoalName(String goalID) {
		// TODO Auto-generated method stub
		Cursor c1 = MainActivity.db.rawQuery(
				"Select * from Main_Goal Where MainGoalID=" + goalID, null);
		String goalName = "";
		while (c1.moveToNext()) {
			goalName = DatabaseUltility.GetColumnValue(c1,
					DatabaseUltility.MainGoalName);
		}
		return goalName;
	}

	private String getGenderName(String genderID) {
		// TODO Auto-generated method stub
		Cursor c1 = MainActivity.db.rawQuery(
				"Select * from Gender Where GenderID=" + genderID, null);
		String genderName = "";
		while (c1.moveToNext()) {
			genderName = DatabaseUltility.GetColumnValue(c1,
					DatabaseUltility.GenderName);
		}
		return genderName;
	}

	private String getFitnessLevelName(String FitnessLevelID) {
		// TODO Auto-generated method stub
		Cursor c1 = MainActivity.db.rawQuery(
				"Select * from FitnessLevel Where FitnessLevelID ="
						+ FitnessLevelID, null);
		String Name = "";
		while (c1.moveToNext()) {
			Name = DatabaseUltility.GetColumnValue(c1,
					DatabaseUltility.FitnessLevelName);
		}
		return Name;
	}
}
