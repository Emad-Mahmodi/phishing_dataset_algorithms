package com.lynda.javatraining.db.beans;

public class website {
	
	private int id;
	private String url;
	private String phish_id;
	private String lable;
	private String htmlContent;

	
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPhish_id() {
		return phish_id;
	}
	public void setPhish_id(String phish_id) {
		this.phish_id = phish_id;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}

}
