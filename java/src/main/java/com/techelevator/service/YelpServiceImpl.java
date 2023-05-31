package com.techelevator.service;

import com.techelevator.model.yelp.Restaurant;
import com.techelevator.model.yelp.RestaurantDetails;
import com.techelevator.model.yelp.YelpResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

/**
 * This is the Yelp Service implementation; any interactions with the Yelp API will be handled here
 */

@Service
public class YelpServiceImpl implements YelpService {

    /**
     * Uses the Yelp API business search endpoint (https://docs.developer.yelp.com/reference/v3_business_search)
     * to search for restaurants based on the given location.
     * @param location the location to search for restaurants in
     * @return a list of Restaurant objects based on the search criteria
     */
    @Override
    public List<Restaurant> getRestaurant(String location, String sort) {
        // The Yelp API endpoint
        String endpoint = "https://api.yelp.com/v3/businesses/search";

        //API key for authentication (technically not good idea to commit Api keys)
        HttpHeaders headers = getHttpHeaders();

        //create RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        //Build API endpoint URL with the location, sort_by, categories
        String url = endpoint + "?location=" + location + "&sort_by=" + sort + "&categories=restaurant";

        //create HttpEntity with header for the Yelp API request
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Yelp API request with RestTemplate. calls url using hte get method and the entity contains the authentication. converts
        // the response into a Yelp Response object
        ResponseEntity <YelpResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, YelpResponse.class);


        return responseEntity.getBody().getBusinesses();
    }


    /**
     * Queries the Yelp API for details about a restaurant with the specified ID, including its hours of operation and other information.
     * @param id the ID of the restaurant to look up on Yelp
     * @return a `RestaurantDetails` object containing information about the restaurant, or `null` if no information was found
     * @throws RestClientException if there is an error communicating with the Yelp API
     */

    @Override
    public RestaurantDetails getRestaurantById(String id) {
        System.out.println("here");
        String url = "https://api.yelp.com/v3/businesses/" + id;

        HttpHeaders headers = getHttpHeaders();

        //create RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        //create HttpEntity with header for the Yelp API request
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        RestaurantDetails restaurantDetails;
        try {
            // Yelp API request with RestTemplate. calls url using the get method and the entity contains the authentication. converts
            // the response into a Yelp Response object
            ResponseEntity <RestaurantDetails> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, RestaurantDetails.class);
            restaurantDetails = responseEntity.getBody();
            // process the response
        } catch (HttpClientErrorException ex) {
            if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Yelp Business Not Found");
            }
            throw new ResponseStatusException(ex.getStatusCode(), "Error during Yelp Call");
        }
        return restaurantDetails;

    }

    /**

     Creates and returns HttpHeaders object with authorization header to authenticate against the Yelp API using a Yelp API key.
     Note: It is not recommended to commit API keys to source code repositories.
     @return HttpHeaders object with authorization header
     */
    //Common function to create authentication against  Yelp
    private  HttpHeaders getHttpHeaders() {
        String apiKey = "IknK0BDcA9mbKNqsIsRRh2b5VsS3uR-rFivvVToXs4zV5Mlut0PA88NwgDA1bZsH4TY9I2R7qqKHidZSSt6YtFO-cqefiBwsoIKq6SmIOo9inQttWZEMTVeZkXMzZHYx";
        String auth = "Bearer " + apiKey;
        // Create HttpHeaders with the authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", auth);
        return headers;
    }
}
