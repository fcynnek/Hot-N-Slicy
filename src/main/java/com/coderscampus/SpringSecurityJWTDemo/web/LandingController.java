package com.coderscampus.SpringSecurityJWTDemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
	
	@GetMapping("/welcome")
	public String getLanding() {
		return "welcome";
	}

}
