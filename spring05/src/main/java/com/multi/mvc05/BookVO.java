package com.multi.mvc05;

public class BookVO {

	private String id;
	private String name;
	private String url;
	private String img;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "BookVO [id=" + id + ", name=" + name + ", url=" + url + ", img=" + img + "]";
	}

}
