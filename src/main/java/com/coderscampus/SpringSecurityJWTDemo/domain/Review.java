package com.coderscampus.SpringSecurityJWTDemo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewId;
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String review;
	
	public Review() {

	}

	public Review(Integer reviewId, Restaurant restaurant, User user, String review) {
		super();
		this.reviewId = reviewId;
		this.restaurant = restaurant;
		this.user = user;
		this.review = review;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", restaurant=" + restaurant + ", user=" + user + ", review=" + review
				+ "]";
	}
	
}

	