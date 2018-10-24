package com.qa.RESTfulAPI.PojoClass;

public class Data {
	private AuthorName authorName;
	private String authorDOY;
	public Data(AuthorName authorName, String authorDOY) {
		super();
		this.authorName = authorName;
		this.authorDOY = authorDOY;
	}
	
	public Data()
	{
		
	}
	
	public AuthorName getAuthorName() {
		return authorName;
	}
	public void setAuthorName(AuthorName authorName) {
		this.authorName = authorName;
	}
	public String getAuthorDOY() {
		return authorDOY;
	}
	public void setAuthorDOY(String authorDOY) {
		this.authorDOY = authorDOY;
	}
}
