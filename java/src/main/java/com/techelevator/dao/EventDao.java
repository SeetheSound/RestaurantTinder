package com.techelevator.dao;

import com.techelevator.model.EventRequest;

import java.time.LocalDateTime;
import java.util.List;


public interface EventDao {

    //Creating an event first
    int createEvent(EventRequest eventRequest, String username);

    // get event
    EventRequest getEvent(int eventId);

    LocalDateTime getRsvpDeadline (int eventId);

    void finalRestaurantSelectionId (int eventId, String yelpRestaurantId);

    List<EventRequest> getEvents(String username);

}
