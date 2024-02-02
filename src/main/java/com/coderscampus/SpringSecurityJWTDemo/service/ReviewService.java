package com.coderscampus.SpringSecurityJWTDemo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coderscampus.SpringSecurityJWTDemo.domain.Review;
import com.coderscampus.SpringSecurityJWTDemo.repository.ReviewRepository;

@Service
public class ReviewService {
	
	private ReviewRepository reviewRepo;

	public ReviewService(ReviewRepository reviewRepo) {
		super();
		this.reviewRepo = reviewRepo;
	}

	public Review findReviewsByRestaurantId(Integer id) {
		Optional <Review> reviewOpt = reviewRepo.findReviewsByRestaurantId(id);
		return reviewOpt.orElse(null);
	}

	public Review saveMessage(Review review) {
		return reviewRepo.save(review);
		
	}
	
	

}
