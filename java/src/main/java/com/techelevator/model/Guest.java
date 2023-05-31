package com.techelevator.model;

public class Guest {
    private int guestEventId;
    private String email;
    private int eventId;

    //constructors


    public Guest(int guestEventId, String email, int eventId) {
        this.guestEventId = guestEventId;
        this.email = email;
        this.eventId = eventId;
    }

    public Guest() {
    }

    //getters and setters


    public int getGuestEventId() {
        return guestEventId;
    }

    public void setGuestEventId(int guestEventId) {
        this.guestEventId = guestEventId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestEventId +
                ", email='" + email + '\'' +
                ", eventId=" + eventId +
                '}';
    }
}



