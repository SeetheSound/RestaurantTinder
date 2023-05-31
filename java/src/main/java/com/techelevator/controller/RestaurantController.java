package com.techelevator.controller;

import com.techelevator.model.yelp.Restaurant;
import com.techelevator.model.yelp.RestaurantDetails;
import com.techelevator.service.YelpService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

/**
 * This Restaurant controller will contain endpoints related to the Restaurant
 */
@RestController
@RequestMapping("/api/restaurant")
@CrossOrigin

public class RestaurantController {

    YelpService yelpService;
    public RestaurantController(YelpService yelpService) {
        this.yelpService = yelpService;
    }

    /**
     * This method returns a list of restaurants in the specified location. The sort parameter allows the user to sort the
     * restaurants by rating, review count, distance or best match. The default value is rating. If an invalid sort value is
     * provided, the method defaults to best_match. This method does not include the hours of the restaurants. The location
     * is provided as a path variable and the sort parameter is provided as a query parameter. This method calls the
     * yelpService.getRestaurant() method to get the list of restaurants based on the location and sort criteria and returns
     * the result.
     *
     * @param location the location of the restaurants
     * @param sort the sort criteria for the restaurants
     * @return the list of restaurants based on the location and sort criteria
     */

    @RequestMapping (value = "/{location}", method = RequestMethod.GET)
    public List<Restaurant> getRestaurantByLocation(@PathVariable String location, @RequestParam (name="sort", defaultValue = "rating", required = false) String sort) {
        if (sort == null || (!sort.equals("best_match") && !sort.equals("rating") && !sort.equals("review_count") && !sort.equals("distance"))) {
            sort = "best_match"; // Set default value
        }
        System.out.println(sort);
        return yelpService.getRestaurant(location, sort);
    }

    /**
     * Returns the details of a restaurant by its ID, including its hours, ratings etc.
     *
     * @param id The ID of the restaurant to retrieve details for.
     * @return The details of the restaurant with the specified ID.
     */
    @RequestMapping (value = "/details/{id}", method = RequestMethod.GET)
    public RestaurantDetails getRestaurantById(@PathVariable String id) {
        return yelpService.getRestaurantById(id);
    }



}
