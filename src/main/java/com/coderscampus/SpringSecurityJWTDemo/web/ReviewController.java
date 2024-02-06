package com.coderscampus.SpringSecurityJWTDemo.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.SpringSecurityJWTDemo.domain.Restaurant;
import com.coderscampus.SpringSecurityJWTDemo.domain.Review;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.repository.RestaurantRepository;
import com.coderscampus.SpringSecurityJWTDemo.service.RestaurantService;
import com.coderscampus.SpringSecurityJWTDemo.service.ReviewService;
import com.coderscampus.SpringSecurityJWTDemo.service.UserServiceImpl;

@RestController
public class ReviewController {

	private ReviewService reviewService;
	private RestaurantService restaurantService;
	private UserServiceImpl userServiceImpl;
	private RestaurantRepository resRepository;

	public ReviewController(ReviewService reviewService, RestaurantService restaurantService,
			UserServiceImpl userServiceImpl, RestaurantRepository resRepository) {
		super();
		this.reviewService = reviewService;
		this.restaurantService = restaurantService;
		this.userServiceImpl = userServiceImpl;
		this.resRepository = resRepository;
	}

	@PostMapping("/restaurants/{id}")
	public Review postMessage(@RequestBody Review review, @PathVariable("id") Integer id)
			throws IllegalAccessException, InvocationTargetException {
		Optional<Restaurant> foundRestaurant = resRepository.findById(id);

		// Create a new object
		Review newReview = new Review();
		BeanUtils.copyProperties(newReview, review);

		// Set rest id
		review.setRestaurantId(id);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		Optional<User> foundUser = userServiceImpl.findUserByEmail(username);
		List<User> users = review.getUser();

		if (foundUser.isPresent()) {
			if (users == null) {
				newReview.setUser(List.of(foundUser.get()));
			} else {
				newReview.getUser().add(foundUser.get());
			}
			if (foundRestaurant.isPresent()) {
				newReview.setRestaurant(foundRestaurant.get());
			}
			foundUser.get().getReviews().add(newReview);
//		userServiceImpl.save(foundUser.get());
			reviewService.saveMessage(newReview);
		}
		System.out.println("Received review: " + review);
		return review;
	}

}
