package com.coderscampus.SpringSecurityJWTDemo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coderscampus.SpringSecurityJWTDemo.domain.Review;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.repository.ReviewRepository;
import com.coderscampus.SpringSecurityJWTDemo.repository.UserRepository;

@Service
public class ReviewService {
	
	private ReviewRepository reviewRepo;
	private UserRepository userRepo;

	public ReviewService(ReviewRepository reviewRepo, UserRepository userRepo) {
		super();
		this.reviewRepo = reviewRepo;
		this.userRepo = userRepo;
	}

	public Review findReviewsByRestaurantId(Integer id) {
		Optional <Review> reviewOpt = reviewRepo.findReviewsByRestaurantId(id);
		return reviewOpt.orElse(null);
	}

	public Review saveMessage(Review review) {
		return reviewRepo.save(review);
		
	}

	public void delete(Integer reviewId) {
		Review review = reviewRepo.findById(reviewId).get();
	    User user = review.getUser().get(0);
	    user.getReviews().remove(review);
	    userRepo.save(user); 
	    reviewRepo.delete(review);  
	    
	}
	
}
