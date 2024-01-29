package com.coderscampus.SpringSecurityJWTDemo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.SpringSecurityJWTDemo.domain.Restaurant;
import com.coderscampus.SpringSecurityJWTDemo.security.FileService;
import com.coderscampus.SpringSecurityJWTDemo.service.RestaurantService;

@Controller
public class RestaurantController {

	private FileService fileService;
	private RestaurantService restaurantService;

	public RestaurantController(FileService fileService, RestaurantService restaurantService) {
		super();
		this.fileService = fileService;
		this.restaurantService = restaurantService;
	}

	@GetMapping("/restaurants/{id}")
	public String getRestaurantById(@PathVariable Integer id, ModelMap model) {
		Restaurant restaurant = restaurantService.findById(id);
		model.put("restaurant", restaurant);
		return "restaurant";
	}
	
	@GetMapping("/restaurants/search")
	public String searchRestaurants(@RequestParam(required=false) String keyword, ModelMap model) {
	    if (keyword != null) {
	        List<Restaurant> searchResults = restaurantService.findByName(keyword);
	        model.put("searchResults", searchResults);
	        model.put("keyword", keyword);
	    }

	    return "homepage";
	}
	
	//Chat GPT code for dynamic drop down
	@GetMapping("/restaurants/suggestions")
	public ResponseEntity<List<Restaurant>> getRestaurantSuggestions(@RequestParam String keyword) {
	    List<Restaurant> suggestions = restaurantService.findByName(keyword);
	    return ResponseEntity.ok(suggestions);
	}

//	@GetMapping("/process-csv")
//	public ResponseEntity<String> processCsv() {
//		fileService.readFile();
//		return ResponseEntity.ok("CSV processing successful!");
//	} 
//	this is calling the method that populated the data and saves to db. 
//  only uncomment when you need to repopulate.	Could set up similar
//  to the AdminController
	
}
