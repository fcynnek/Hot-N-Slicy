package com.coderscampus.SpringSecurityJWTDemo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.coderscampus.SpringSecurityJWTDemo.security.FileService;

@Controller
public class RestaurantController {

	private FileService fileService;

	public RestaurantController(FileService fileService) {
		super();
		this.fileService = fileService;
	}

	@GetMapping("/restaurants")
	public String getSearch(ModelMap model) {
		return "restaurant";
	}

//	@GetMapping("/process-csv")
//	public ResponseEntity<String> processCsv() {
//		fileService.readFile();
//		return ResponseEntity.ok("CSV processing successful!");
//	} this is calling the method that populated the data and saves to db. 
//  only uncomment when you need to repopulate.	
	
}
