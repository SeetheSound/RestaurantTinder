package com.techelevator.model.yelp;


/**
 * Category is nested in the businesses of Yelp Response so it got its own class
 */

public class Category {

    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}