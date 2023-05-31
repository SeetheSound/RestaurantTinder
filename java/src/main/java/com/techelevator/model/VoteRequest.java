package com.techelevator.model;

public class VoteRequest {

private int eventId;
private String guestEmail;
private boolean vote;
private String yelpRestaurantId;
private String guestComments;


//constructor
    public VoteRequest(int eventId, String guestEmail, boolean vote, String yelpRestaurantId, String guestComments) {
        this.eventId = eventId;
        this.guestEmail = guestEmail;
        this.vote = vote;
        this.yelpRestaurantId = yelpRestaurantId;
        this.guestComments = guestComments;
    }


    //getters and setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestId(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public String getYelpRestaurantId() {
        return yelpRestaurantId;
    }

    public void setYelpRestaurantId(String yelpRestaurantId) {
        this.yelpRestaurantId = yelpRestaurantId;
    }

    public String getGuestComments() {
        return guestComments;
    }

    public void setGuestComments(String guestComments) {
        this.guestComments = guestComments;
    }

    @Override
    public String toString() {
        return "VoteRequest{" +
                "eventId=" + eventId +
                ", guestEmail=" + guestEmail +
                ", vote=" + vote +
                ", restaurantId='" + yelpRestaurantId + '\'' +
                ", guestComments='" + guestComments + '\'' +
                '}';
    }
}
