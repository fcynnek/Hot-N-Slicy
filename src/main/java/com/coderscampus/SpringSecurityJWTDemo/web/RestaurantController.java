package com.coderscampus.SpringSecurityJWTDemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {
    
    @GetMapping("/restaurants")
    public String getSearch(ModelMap model) {
    	return "restaurant";
    }
    
}
