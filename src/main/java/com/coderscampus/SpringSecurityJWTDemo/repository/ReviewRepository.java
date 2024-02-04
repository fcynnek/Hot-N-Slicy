package com.coderscampus.SpringSecurityJWTDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.SpringSecurityJWTDemo.domain.Review;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;

public interface ReviewRepository  extends JpaRepository<Review, Integer> {

	Optional<Review> findReviewsByRestaurantId(Integer id);

	User save(String username);  



}
