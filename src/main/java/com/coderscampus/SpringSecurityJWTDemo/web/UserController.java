package com.coderscampus.SpringSecurityJWTDemo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.SpringSecurityJWTDemo.domain.Restaurant;
import com.coderscampus.SpringSecurityJWTDemo.domain.Review;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.service.UserServiceImpl;

@Controller
public class UserController {
	
	private UserServiceImpl userServiceImpl;

	public UserController(UserServiceImpl userServiceImpl) {
		super();
		this.userServiceImpl = userServiceImpl;
	}
	
	@GetMapping("/homepage")
	public String successfulLogin (ModelMap model) {
		model.addAttribute("searchResults", new ArrayList<Restaurant>());
		return "homepage";
	}

	@GetMapping("/users/profile")
	public String getUser(ModelMap model, Restaurant restaurant) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
	    Optional<User> userOpt = userServiceImpl.findUserByEmail(username); 
	    if (userOpt.isPresent()) {
	    	model.addAttribute("user", userOpt.get());
	    	model.addAttribute("email", userOpt.get().getEmail());
	    	List<Review> resReviews = userOpt.get().getReviews();
	        model.addAttribute("resReviews", resReviews);
	    }
		return "userProfile";
	}
	
}

