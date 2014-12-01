package com.fitnessgear.model;

public class FilterItem {
	private String titleFilter;
	private String valueFilter;

	public FilterItem(String valueFilter) {
		super();
		this.valueFilter = valueFilter;
	}

	public FilterItem(String titleFilter, String valueFilter) {
		super();
		this.titleFilter = titleFilter;
		this.valueFilter = valueFilter;
	}

	public String getTitleFilter() {
		return titleFilter;
	}

	public void setTitleFilter(String titleFilter) {
		this.titleFilter = titleFilter;
	}

	public String getValueFilter() {
		return valueFilter;
	}

	public void setValueFilter(String valueFilter) {
		this.valueFilter = valueFilter;
	}

}
