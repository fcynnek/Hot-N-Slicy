package com.coderscampus.SpringSecurityJWTDemo.dto;

public class ReviewModel {
	
	private String reviewContents;
	private String username;
	
	public ReviewModel(String reviewContents, String username) {
		super();
		this.reviewContents = reviewContents;
		this.username = username;
	}
	public String getReviewContents() {
		return reviewContents;
	}
	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
