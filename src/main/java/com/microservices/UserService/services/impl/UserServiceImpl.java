package com.microservices.UserService.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.UserService.entities.Hotel;
import com.microservices.UserService.entities.Rating;
import com.microservices.UserService.entities.User;
import com.microservices.UserService.external.service.HotelService;
import com.microservices.UserService.repositories.UserRepository;
import com.microservices.UserService.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	 
	@Override
	public User saveUser(User user) {
		String randomId = UUID.randomUUID().toString();
		user.setUserId(randomId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
		
		ResponseEntity<ArrayList<Rating>> ratingResponse = restTemplate.exchange("http://RATING-SERVICE/ratings/user/" + user.getUserId(),
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<ArrayList<Rating>>() {});
		ArrayList<Rating> ratingList = ratingResponse.getBody();
		List<Rating> ratings = ratingList.stream().map(rating -> {
			//ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratings);
		return user;
	}

}
