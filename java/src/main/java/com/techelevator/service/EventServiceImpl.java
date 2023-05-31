package com.techelevator.service;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.GuestEventDao;
import com.techelevator.model.EventRequest;
import com.techelevator.model.Guest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

final EventDao eventDao;

final GuestEventDao guestEventDao;

//constructor
    public EventServiceImpl(EventDao eventDao, GuestEventDao guestEventDao) {
        this.eventDao = eventDao;
        this.guestEventDao = guestEventDao;
    }


    /**
     * Updates the final restaurant selection for an event with the given ID.
     * Checks if the given restaurant ID and event ID exist in their respective DAOs.
     * If they do, updates the event with the selected restaurant ID.
     * Throws ResponseStatusException with HttpStatus.NOT_FOUND if the restaurant ID or event ID is not found.
     *
     * @param eventId The ID of the event to update the final restaurant selection for.
     * @param yelpRestaurantId The ID of the restaurant selected for the event.
     * @throws ResponseStatusException if the restaurant ID or event ID is not found.
     */
    @Override
    public void updateFinalRestaurantSelection(Integer eventId, String yelpRestaurantId) {
        try {
              eventDao.getEvent(eventId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event ID not found");
        }
        eventDao.finalRestaurantSelectionId(eventId, yelpRestaurantId);

    }

    @Override
    public EventRequest createEvent(EventRequest eventRequest, String username) {
        int eventId = eventDao.createEvent(eventRequest, username);
        return getEventById(eventId);
    }
    /**
     * Retrieves an event request with the specified ID from the event DAO.
     *
     * @param eventId the ID of the event request to retrieve
     * @return the event request with the specified ID, with its restaurants field set to the associated restaurant IDs
     * @throws Exception if an error occurs while retrieving the event request or its associated restaurant IDs
     */
    @Override
    public EventRequest getEventById(int eventId) {
        EventRequest eventRequest = eventDao.getEvent(eventId);
        List<Guest> guests = guestEventDao.getGuest(eventId);
        eventRequest.setGuests(guests);
        return eventRequest;
    }

    @Override
    public void addGuest(List<String> guestEmail, int eventId) {
        guestEventDao.createGuestEvent(eventId, guestEmail);

    }

    @Override
    public List<EventRequest> getEvents(String username) {
        List<EventRequest> eventRequestList = eventDao.getEvents(username);
        for(EventRequest eventRequest: eventRequestList) {
            List<Guest> guests = guestEventDao.getGuest(eventRequest.getEventId());
            eventRequest.setGuests(guests);
        }
        return eventRequestList;
    }

}
