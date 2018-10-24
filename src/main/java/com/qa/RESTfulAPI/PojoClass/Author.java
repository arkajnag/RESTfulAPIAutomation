package com.qa.RESTfulAPI.PojoClass;

import java.util.List;

public class Author {
	private List<Data> data;

	public Author(List<Data> data) {
		super();
		this.data = data;
	}
	public Author()
	{
		
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
}
