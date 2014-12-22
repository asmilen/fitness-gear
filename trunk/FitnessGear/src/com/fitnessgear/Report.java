package com.fitnessgear;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitnessgear.database.DatabaseUltility;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.ArcValue;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ColumnValue;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SimpleValueFormatter;
import lecho.lib.hellocharts.util.Utils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;
import lecho.lib.hellocharts.view.PieChartView.PieChartOnValueTouchListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Report extends Fragment {

	public Report() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_report, container,
				false);
		getFragmentManager().beginTransaction().add(R.id.container, new ColumnChartFragment()).commit();
		getFragmentManager().beginTransaction().add(R.id.containerpiechart, new PieChartFragment()).commit();
		return rootView;
	}

	public static class ColumnChartFragment extends Fragment {

		final private int NUMBER_OF_COLUMN = 14; // 2 weeks
		private ColumnChartView chart;
		private ColumnChartData data;
		private boolean hasLabels = true;
		private boolean hasLabelForSelected = false;

		public ColumnChartFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			setHasOptionsMenu(true);
			View rootView = inflater.inflate(R.layout.fragment_column_chart,
					container, false);

			chart = (ColumnChartView) rootView.findViewById(R.id.chart);
			chart.setValueSelectionEnabled(hasLabelForSelected);
			generateDefaultData();

			return rootView;
		}

		private void generateDefaultData() {
			// Get day
			Calendar c = Calendar.getInstance();

			int numSubcolumns = 1;
			int numColumns = NUMBER_OF_COLUMN;
			// Column can have many subcolumns, here by default I use 1
			// subcolumn in each of 8 columns.
			List<Column> columns = new ArrayList<Column>();
			List<ColumnValue> values;
			for (int i = 0; i < numColumns; ++i) {

				values = new ArrayList<ColumnValue>();
				for (int j = 0; j < numSubcolumns; ++j) {
					String DayID = (c.get(Calendar.DAY_OF_MONTH) - (numColumns
							- i - 1))
							+ "/"
							+ (c.get(Calendar.MONTH) + 1)
							+ "/"
							+ c.get(Calendar.YEAR);
					values.add(new ColumnValue(DatabaseUltility
							.GetTotalKgsDay(DayID), Color.parseColor("#024f91")));
				}

				Column column = new Column(values);
				column.setHasLabels(hasLabels);
				column.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(column);
			}

			// Set axis X,Y
			data = new ColumnChartData(columns);

			ArrayList<AxisValue> mylist = new ArrayList<AxisValue>();
			for (int i = 0; i < numColumns; i++)
				mylist.add(new AxisValue(numColumns-i));

			Axis axisX = new Axis();
			axisX.setName("Weight Lifted Over Time(14 Days)");
			axisX.setValues(mylist);
			data.setAxisXBottom(axisX);

			chart.setColumnChartData(data);

		}

	}
	
	/**
	 * A fragment containing a pie chart.
	 */
	public static class PieChartFragment extends Fragment {

		private PieChartView chart;
		private PieChartData data;

		private boolean hasLabels = true;
		private boolean hasLabelsOutside = false;
		private boolean hasCenterCircle = true;
		private boolean hasLabelForSelected = false;

		public PieChartFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			setHasOptionsMenu(true);
			View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);

			chart = (PieChartView) rootView.findViewById(R.id.pieChart);
			chart.setOnValueTouchListener(new ValueTouchListener());

			generateData();

			return rootView;
		}

		
		
		private void generateData() {
			int numValues = 6;
			String[] labels = new String[]{"Chest","triceps","biceps","legs","arm","neck"};
			List<ArcValue> values = new ArrayList<ArcValue>();
			for (int i = 0; i < numValues; ++i) {
				ArcValue arcValue = new ArcValue((float) Math.random() * 30 + 15, Utils.pickColor());
				
				//setting label manually:
				arcValue.setLabel(labels[i].toCharArray());				
				values.add(arcValue);
			}

			data = new PieChartData(values);
			data.setHasLabels(hasLabels);
			data.setHasLabelsOnlyForSelected(hasLabelForSelected);
			data.setHasLabelsOutside(hasLabelsOutside);
			data.setHasCenterCircle(hasCenterCircle);
			//value formatter with custom parameters
			data.setFormatter(new SimpleValueFormatter(1, false, null, "%".toCharArray()));
			chart.setPieChartData(data);
		}

		
		
		private class ValueTouchListener implements PieChartOnValueTouchListener {

			@Override
			public void onValueTouched(int selectedArc, ArcValue value) {
				Toast.makeText(getActivity(), "Selected: " + value.getValue(), Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onNothingTouched() {
				// TODO Auto-generated method stub

			}

		}
	}
}


