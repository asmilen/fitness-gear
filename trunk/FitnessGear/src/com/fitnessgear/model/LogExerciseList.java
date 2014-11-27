package com.fitnessgear.model;

import java.util.ArrayList;

public class LogExerciseList {
	private ArrayList<LogExerciseItem> myLogExerciseList;

	public ArrayList<LogExerciseItem> getMyLogExerciseList() {
		return myLogExerciseList;
	}

	public void setMyLogExerciseList(ArrayList<LogExerciseItem> myLogExerciseList) {
		this.myLogExerciseList = myLogExerciseList;
	}

	public void add(LogExerciseItem item) {
		myLogExerciseList.add(item);
	}

	public int getSize()
	{
		return myLogExerciseList.size();
	}
	
	public LogExerciseList() {
		super();
		this.myLogExerciseList = new ArrayList<LogExerciseItem>();
	}

	public void updateItem(int index, LogExerciseItem item)
	{
		myLogExerciseList.set(index, item);
	}
}
