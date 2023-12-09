package com.microservices.UserService.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.UserService.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	@PostMapping("/ratings/")
	public Rating createRating(@RequestBody Rating rating);
	
}
