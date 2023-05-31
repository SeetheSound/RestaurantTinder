package com.techelevator.model;

import com.techelevator.model.yelp.RestaurantDetails;
import com.techelevator.service.YelpService;

public class RestaurantVoteResult {
    private String restaurantYelpId;
    private  int netVoteCount;

    private RestaurantDetails restaurantDetails;


    /**
     * Creates a new RestaurantVoteResult object that represents the vote result of a restaurant.
     *
     * @param restaurantYelpId - an String value representing the ID of the restaurant
     * @param netVoteCount - an integer value representing the net vote count (upvotes minus downvotes) for the restaurant
     */
    public RestaurantVoteResult(String restaurantYelpId, int netVoteCount) {
        this.restaurantYelpId = restaurantYelpId;
        this.netVoteCount = netVoteCount;
    }


    //getters and setters
    public String getRestaurantYelpId() {
        return restaurantYelpId;
    }

    public int getNetVoteCount() {
        return netVoteCount;
    }

    public RestaurantDetails getRestaurantDetails() {
        return restaurantDetails;
    }

    public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
        this.restaurantDetails = restaurantDetails;
    }

    @Override
    public String toString() {
        return "RestaurantVoteResult{" +
                "restaurantYelpId='" + restaurantYelpId + '\'' +
                ", netVoteCount=" + netVoteCount +
                ", restaurantDetails=" + restaurantDetails +
                '}';
    }
}
