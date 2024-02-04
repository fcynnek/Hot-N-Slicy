package com.coderscampus.SpringSecurityJWTDemo.web;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.SpringSecurityJWTDemo.domain.Review;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.service.RestaurantService;
import com.coderscampus.SpringSecurityJWTDemo.service.ReviewService;
import com.coderscampus.SpringSecurityJWTDemo.service.UserService;
import com.coderscampus.SpringSecurityJWTDemo.service.UserServiceImpl;

@RestController
public class ReviewController {
	
	private ReviewService reviewService;
	private RestaurantService restaurantService;
	private UserServiceImpl userServiceImpl;


	public ReviewController(ReviewService reviewService, RestaurantService restaurantService, UserServiceImpl userServiceImpl) {
		super();
		this.reviewService = reviewService;
		this.restaurantService = restaurantService;
		this.userServiceImpl = userServiceImpl;
	}


	@PostMapping("/restaurants/{id}")
	public Review postMessage(@RequestBody Review review, User user, @PathVariable ("id") Integer id) {
		System.out.println(id);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
	    review.setUser(List.of(user.email(username)));
	    user.getReviews().add(review);
	    
	    
//	    Restaurant restaurant = restaurantService.findById(id);
//	    review.setRestaurant(restaurant);  // This didnt work lol, says id is null
	    userServiceImpl.save(user);
		reviewService.saveMessage(review);
		
		System.out.println("Received review: " + review);
		return review;
	}

}
