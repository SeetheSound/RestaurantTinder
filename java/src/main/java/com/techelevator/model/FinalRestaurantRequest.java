package com.techelevator.model;

public class FinalRestaurantRequest {

    String yelpRestaurantId;
    Integer eventId;

    //constructor
    public FinalRestaurantRequest(String yelpRestaurantId, Integer eventId) {
        this.yelpRestaurantId = yelpRestaurantId;
        this.eventId = eventId;
    }
    //getters and setters
    public String getYelpRestaurantId() {
        return yelpRestaurantId;
    }

    public void setYelpRestaurantId(String yelpRestaurantId) {
        this.yelpRestaurantId = yelpRestaurantId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "FinalRestaurantRequest{" +
                "yelpRestaurantId='" + yelpRestaurantId + '\'' +
                ", eventId=" + eventId +
                '}';
    }
}
