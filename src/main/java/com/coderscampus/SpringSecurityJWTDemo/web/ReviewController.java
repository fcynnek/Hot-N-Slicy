package com.coderscampus.SpringSecurityJWTDemo.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.SpringSecurityJWTDemo.domain.Review;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.service.ReviewService;

@RestController
public class ReviewController {
	
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}


	@PostMapping("/restaurants/{id}")
	public Review postMessage(@RequestBody Review review, User user) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
	    review.setUser(user.email(username));
	    
//	    Restaurant restaurant = restaurantService.findById(restaurantId);
//	    review.setRestaurant(restaurant);
	    
//	    String existingReview = review.getReview();
//	    review.setReview(existingReview);
	    
		reviewService.saveMessage(review);
		
		System.out.println("Received review: " + review);
		return review;
	}

}
