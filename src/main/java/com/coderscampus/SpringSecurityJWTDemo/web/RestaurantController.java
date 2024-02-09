package com.coderscampus.SpringSecurityJWTDemo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.SpringSecurityJWTDemo.domain.Restaurant;
import com.coderscampus.SpringSecurityJWTDemo.domain.Review;
import com.coderscampus.SpringSecurityJWTDemo.dto.ReviewModel;
import com.coderscampus.SpringSecurityJWTDemo.security.FileService;
import com.coderscampus.SpringSecurityJWTDemo.service.RestaurantService;
import com.coderscampus.SpringSecurityJWTDemo.service.ReviewService;

@Controller
@CrossOrigin(origins = "*")
public class RestaurantController {

	private FileService fileService;
	private RestaurantService restaurantService;
	private ReviewService reviewService;

	public RestaurantController(FileService fileService, RestaurantService restaurantService,
			ReviewService reviewService) {
		super();
		this.fileService = fileService;
		this.restaurantService = restaurantService;
		this.reviewService = reviewService;
	}

	@GetMapping("/restaurants/{restaurantId}")
	public String getRestaurantById(@PathVariable Integer restaurantId, ModelMap model) {
		Restaurant restaurant = restaurantService.findById(restaurantId);
		List<Review> resReviews = restaurant.getReviews();
		if (resReviews != null) {
			model.put("resReviews", resReviews);
		}
		model.put("restaurant", restaurant);
		return "restaurant";
	}

	@GetMapping("/restaurants/search")
	public String searchRestaurants(@RequestParam(required = false) String keyword, ModelMap model) {
		if (keyword != null) {
			List<Restaurant> searchResults = restaurantService.findByName(keyword);
			model.put("searchResults", searchResults);
			model.put("keyword", keyword);
		}
		return "homepage";
	}

	@GetMapping("/restaurants/suggestions")
	public ResponseEntity<List<Restaurant>> getRestaurantSuggestions(@RequestParam String keyword) {
		List<Restaurant> suggestions = restaurantService.findByName(keyword);
		return ResponseEntity.ok(suggestions);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/get-all-reviews")
	@ResponseBody
	public List<ReviewModel> getAllReviews(@RequestBody Integer id) {
		Restaurant foundRestaurant = restaurantService.findById(id);
		List<Review> reviews = new ArrayList<>(foundRestaurant.getReviews());
		List<ReviewModel> reviewsContent = reviews.stream()
		.map(item -> 
			new ReviewModel(item.getReviewContents(), "Test")
		)
		.collect(Collectors.toList());
		return reviewsContent;
	}

//	@GetMapping("/process-csv")
//	public ResponseEntity<String> processCsv() {
//		fileService.readFile();
//		return ResponseEntity.ok("CSV processing successful!");
//	}
}