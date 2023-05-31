package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.List;

public class EventRequest {

    private String eventName;
    private String description;

    private String location;
    private LocalDateTime eventDate;
    private LocalDateTime rsvpDeadline;
    private int hostUserId;
    private int eventId;

    private String finalSelectionRestaurantId =null;

    private List<Guest> guests;



    //constructor
    public EventRequest(Integer eventId, String eventName, String description, LocalDateTime eventDate, LocalDateTime rsvpDeadline, int hostUserId, String location, String finalSelectionRestaurantId, List guest) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.rsvpDeadline = rsvpDeadline;
        this.hostUserId= hostUserId;
        this.eventId = eventId;
        this.location = location;
        this.finalSelectionRestaurantId = finalSelectionRestaurantId;
        this.guests = guest;

    }

    public EventRequest() {
    }

    //getters and setters


    public int getHostUserId() {
        return hostUserId;
    }

    public void setHostUserId(int userId) {
        this.hostUserId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }


    public LocalDateTime getRsvpDeadline() {
        return rsvpDeadline;
    }

    public void setRsvpDeadline(LocalDateTime rsvpDeadline) {
        this.rsvpDeadline = rsvpDeadline;
    }

    public String getFinalSelectionRestaurantId() {
        return finalSelectionRestaurantId;
    }

    public void setFinalSelectionRestaurantId(String finalSelectionRestaurantId) {
        this.finalSelectionRestaurantId = finalSelectionRestaurantId;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "EventRequest{" +
                "eventName='" + eventName + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", eventDate=" + eventDate +
                ", rsvpDeadline=" + rsvpDeadline +
                ", hostUserId=" + hostUserId +
                ", eventId=" + eventId +
                '}';
    }
}
