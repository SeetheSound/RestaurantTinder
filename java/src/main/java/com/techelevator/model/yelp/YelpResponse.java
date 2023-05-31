package com.techelevator.model.yelp;

import java.util.List;


/**
 * When you call the Yelp endpoint you get "Businesses array" back with a lot of business information inside of
 * it.  Created a representation of a list of businesses. This response contains a list of businesses and a total count.
 * This java object was created to represent what was coming back. You can read the documentation on this at https://docs.developer.yelp.com/reference/v3_business_search
 * or by using Postman.
 */

public class YelpResponse {

    private List<Restaurant> businesses;
    private int total;
    public List<Restaurant> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Restaurant> businesses) {
        this.businesses = businesses;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}

