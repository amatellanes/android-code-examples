package com.amatellanes.pelicularest;

public class Item {

	private Integer image;
	private String title;
	private String url;

	public Item() {
		super();
	}

	public Item(Integer image, String title, String url) {
		super();
		this.image = image;
		this.title = title;
		this.url = url;
	}

	public Integer getImage() {
		return image;
	}

	public void setImage(Integer image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}