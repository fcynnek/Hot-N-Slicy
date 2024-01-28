package com.coderscampus.SpringSecurityJWTDemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

//	@GetMapping("/process-csv")
//	public ResponseEntity<String> processCsv() {
//		fileService.readFile();
//		return ResponseEntity.ok("CSV processing successful!");
//	} 
//	this is calling the method that populated the data and saves to db. 
//  only uncomment when you need to repopulate.	Could set up similar
//  to the AdminController
	
}
