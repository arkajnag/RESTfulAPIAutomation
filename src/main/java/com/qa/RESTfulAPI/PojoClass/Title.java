package com.qa.RESTfulAPI.PojoClass;

import java.util.List;

public class Title {
	private String name;
	private String editor;
	private Address address;
	private String year;
	private List<String> launchingCity=null;
	
	public Title(String name, String editor, Address address, String year, List<String> launchingCity) {
		super();
		this.name = name;
		this.editor = editor;
		this.address = address;
		this.year = year;
		this.launchingCity = launchingCity;
	}
	
	public Title()
	{
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<String> getLaunchingCity() {
		return launchingCity;
	}
	public void setLaunchingCity(List<String> launchingCity) {
		this.launchingCity = launchingCity;
	}
}
