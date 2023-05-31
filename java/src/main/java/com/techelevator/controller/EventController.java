package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.EventRequest;
import com.techelevator.model.FinalRestaurantRequest;
import com.techelevator.model.GuestRequest;
import com.techelevator.model.yelp.RestaurantDetails;
import com.techelevator.service.EventService;
import com.techelevator.service.YelpService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@CrossOrigin
public class EventController {

    final EventService eventService;
    final YelpService yelpService;

    public EventController (EventService eventService, EventDao eventDao, UserDao userDao, YelpService yelpService) {
        this.eventService = eventService;
        this.yelpService = yelpService;
    }


    //creates the event and gives the restaurants list
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public EventRequest createEventInvitation(@RequestBody EventRequest eventRequest, Principal principal) {
        return eventService.createEvent(eventRequest, principal.getName());
    }

    /**
     * Retrieves the event request with the specified ID.
     * @param eventId the ID of the event request to retrieve
     * @return the event request with the specified ID
     * @throws ResponseStatusException if the event request with the specified ID is not found
     * @throws Exception if an error occurs while retrieving the event request
     */
    @GetMapping("/{eventId}")
//    @PreAuthorize("isAuthenticated()")
    public EventRequest getEventById(@PathVariable int eventId) {
        EventRequest eventRequest = eventService.getEventById(eventId);
        if (eventRequest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        return eventRequest;
    }

    /***
     *   POST endpoint to update the final restaurant selection for an event.
     *   Only authenticated users are allowed to access this endpoint.
     *   @param finalRestaurantRequest The request body containing the event ID and the restaurant ID.
     *   @throws ResponseStatusException with HttpStatus.NOT_FOUND if the event ID or restaurant ID is not found.
     */

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (method = RequestMethod.POST, path = "/final")
    @PreAuthorize("isAuthenticated()")
    public void finalRestaurantLocation (@RequestBody FinalRestaurantRequest finalRestaurantRequest) {
        System.out.println(finalRestaurantRequest);
        RestaurantDetails restaurantDetails = yelpService.getRestaurantById(finalRestaurantRequest.getYelpRestaurantId());
        if(restaurantDetails == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Yelp Restaurant ID not found");
        }
        eventService.updateFinalRestaurantSelection(finalRestaurantRequest.getEventId(), finalRestaurantRequest.getYelpRestaurantId());
    }

    /**
     * The guest request contains guest emails as a list and the event ID. This allows us to associate guests with an event.
     * @param guestRequest
     */

    @RequestMapping (method = RequestMethod.POST, path = "/guests")
    @PreAuthorize("isAuthenticated()")
    public void addGuestsToEvent (@RequestBody GuestRequest guestRequest) {
        System.out.println(guestRequest);
        eventService.addGuest(guestRequest.getEmails(), guestRequest.getEventId());
    }

    @GetMapping("/host/{username}")
    @PreAuthorize("isAuthenticated()")
    public List<EventRequest> getEventsByUserId(@PathVariable String username) {
        return eventService.getEvents(username);
    }



}
