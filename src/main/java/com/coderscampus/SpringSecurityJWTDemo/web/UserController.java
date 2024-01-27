package com.coderscampus.SpringSecurityJWTDemo.web;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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
	public String successfulLogin () {
		return "homepage";
	}

	@GetMapping("/users/profile")
	public String getUser(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
	    Optional<User> userOpt = userServiceImpl.findUserByEmail(username); 
	    if (userOpt.isPresent()) {
	    	model.addAttribute("user", userOpt.get());
	    	model.addAttribute("email", userOpt.get().getEmail());
	    }
		return "userProfile";
	}

}

