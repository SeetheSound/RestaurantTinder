package com.techelevator.service;

import com.techelevator.model.EventRequest;
import jdk.jfr.Event;

import java.util.List;

public interface EventService {

    void updateFinalRestaurantSelection (Integer eventId, String restaurantId);

    EventRequest createEvent(EventRequest eventRequest, String userName);


    EventRequest getEventById(int eventId);

    void addGuest(List<String> guestEmail, int eventId);

    List<EventRequest> getEvents(String username);


}
