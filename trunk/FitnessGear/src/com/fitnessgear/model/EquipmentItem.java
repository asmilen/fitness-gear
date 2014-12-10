package com.fitnessgear.model;

public class EquipmentItem {
	private int equipmentID;
	private String equipmentName;

	public EquipmentItem(int equipmentID, String equipmentName) {
		super();
		this.equipmentID = equipmentID;
		this.equipmentName = equipmentName;
	}

	public int getEquipmentID() {
		return equipmentID;
	}

	public void setEquipmentID(int equipmentID) {
		this.equipmentID = equipmentID;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getEquipmentName();
	}
}
