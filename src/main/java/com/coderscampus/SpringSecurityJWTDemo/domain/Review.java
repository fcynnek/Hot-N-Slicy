package com.coderscampus.SpringSecurityJWTDemo.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
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
	@Column(name = "review_id")
	private Integer reviewId;
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	@JsonIgnoreProperties
	private Restaurant restaurant;
	@ManyToMany(mappedBy = "reviews", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<User> user;
	private String reviewContents;
	@Column(name = "rest_id")

	private Integer restaurantId;

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Review() {
	}

	public Review(List<User> user, String reviewContents, Integer restaurantId, Restaurant restaurant) {
		this.user = user;
		this.reviewContents = reviewContents;
		this.restaurantId = restaurantId;
		this.restaurant = restaurant;
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

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
}