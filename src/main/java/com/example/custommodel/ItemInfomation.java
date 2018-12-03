package com.example.custommodel;

public class ItemInfomation {
	private String title;
	private String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ItemInfomation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemInfomation(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	
}
