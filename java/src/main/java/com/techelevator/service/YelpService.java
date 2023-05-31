package com.techelevator.service;

import com.techelevator.model.yelp.Restaurant;
import com.techelevator.model.yelp.RestaurantDetails;

import java.util.List;


/**
 * Defines the contract of whoever implements this class. YelpServiceImp will implement this
 */
public interface YelpService {

    List<Restaurant> getRestaurant(String location, String sort);

    RestaurantDetails getRestaurantById (String id);


}
