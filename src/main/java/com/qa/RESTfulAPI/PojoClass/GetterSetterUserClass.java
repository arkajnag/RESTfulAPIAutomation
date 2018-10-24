package com.qa.RESTfulAPI.PojoClass;

public class GetterSetterUserClass {
	private String id;
	private Title title;
	private Author author;
	public GetterSetterUserClass(String id, Title title, Author author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}
	
	public GetterSetterUserClass()
	{
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
}
