package com.fitnessgear.model;

public class LogNoteItem {
	private String day;
	private int noteID;
	private String noteName;
	private String noteDetail;

	public LogNoteItem(String day, int noteID, String noteDetail) {
		super();
		this.day = day;
		this.noteID = noteID;
		this.noteDetail = noteDetail;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getNoteID() {
		return noteID;
	}

	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}

	public String getNoteDetail() {
		return noteDetail;
	}

	public void setNoteDetail(String noteDetail) {
		this.noteDetail = noteDetail;
	}

}
