package com.coderscampus.SpringSecurityJWTDemo.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
	@ManyToMany
	@JoinColumn(name = "user_id")
	private List<User> user;
	private String reviewContents;
	@Column(name = "rest_id")
	private Integer restaurantId;

	public Review() {

	}

	public Review(Integer reviewId, Restaurant restaurant, List<User> user, String reviewContents,
			Integer restaurantId) {
		super();
		this.reviewId = reviewId;
		this.restaurant = restaurant;
		this.user = user;
		this.reviewContents = reviewContents;
		this.restaurantId = restaurantId;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<User> getUser() {
		return user;
	}

	public String getReviewContents() {
		return reviewContents;
	}

	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", user=" + user + ", reviewContents=" + reviewContents
				+ ", restaurantId=" + restaurantId + "]";
	}

}
