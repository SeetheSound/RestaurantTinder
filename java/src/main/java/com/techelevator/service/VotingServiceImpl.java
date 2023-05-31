package com.techelevator.service;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.GuestEventDao;
import com.techelevator.dao.RestaurantVotingDao;
import com.techelevator.model.RestaurantVoteResult;
import com.techelevator.model.yelp.RestaurantDetails;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotingServiceImpl implements VotingService {

    final RestaurantVotingDao restaurantVotingDao;
    final EventDao eventDao;
    final GuestEventDao guestEventDao;
    final YelpService yelpService;

    //constructor
    public VotingServiceImpl(RestaurantVotingDao restaurantVotingDao, EventDao eventDao, GuestEventDao guestEventDao, YelpService yelpService) {
        this.restaurantVotingDao = restaurantVotingDao;
        this.eventDao = eventDao;
        this.guestEventDao = guestEventDao;
        this.yelpService = yelpService;
    }

    /**
     * Records a guest's vote for a restaurant, but only if the current time is before the RSVP deadline for the event.
     *
     * @param eventId       the ID of the event for which the guest is voting
     * @param guestEmail       the email of the guest who is voting
     * @param restaurantYelpId  the ID of the restaurant being voted for
     * @param vote          a boolean indicating whether the guest has voted in favor of the restaurant or not
     * @throws ResponseStatusException if the event ID is not found or if the RSVP deadline for voting has passed
     */

    @Override
    public void recordVote(int eventId, String guestEmail, String restaurantYelpId, boolean vote) {
        LocalDateTime rsvpDeadline;
        try {
            rsvpDeadline = eventDao.getRsvpDeadline(eventId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event ID not found");
        }

        //Call get restaurant to validate that yelp id is valid.
        yelpService.getRestaurantById(restaurantYelpId);

        LocalDateTime currentTime = LocalDateTime.now();
        if (rsvpDeadline != null && currentTime.isBefore(rsvpDeadline)) {
            int guestEventId = guestEventDao.findGuestID(eventId, guestEmail);
            restaurantVotingDao.recordVote(eventId, guestEventId, restaurantYelpId, vote);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The RSVP deadline for voting has passed for this event.");
        }
    }

    @Override
    public void recordComment(int eventId, String guestEmail, String restaurantYelpId, String guestComments) {
        LocalDateTime rsvpDeadline;
        try {
            rsvpDeadline = eventDao.getRsvpDeadline(eventId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event ID not found");
        }

        //Call get restaurant to validate that yelp id is valid.
        yelpService.getRestaurantById(restaurantYelpId);

        LocalDateTime currentTime = LocalDateTime.now();
        if (rsvpDeadline != null && currentTime.isBefore(rsvpDeadline)) {
            int guestEventId = guestEventDao.findGuestID(eventId, guestEmail);
            restaurantVotingDao.recordComment(eventId, guestEventId, restaurantYelpId, guestComments);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The RSVP deadline for voting has passed for this event.");
        }
    }

    /**
     * The DAO queries the database and gets back a list of restaurant vote results. The vote results contain a restaurant ID, the net count (the sum of all the trues-false)
     * Iterate through the lists of restaurant vote results and using the restaurant ID it retrieves the Yelp ID.
     * Using the Yelp Service .get restaurant by ID to query Yelp for the business details and set it on the current restaurant vote results
     * Returns the list of restaurant votes
     * @param eventId
     * @return
     */
    @Override
    public List<RestaurantVoteResult> getFinalists(int eventId) {
        List<RestaurantVoteResult> restaurantVoteResultList = restaurantVotingDao.getFinalists(eventId);
        System.out.println("length of r vote: " + restaurantVoteResultList.size());
        for(RestaurantVoteResult restaurantVoteResult: restaurantVoteResultList) {
            RestaurantDetails restaurantDetails = yelpService.getRestaurantById(restaurantVoteResult.getRestaurantYelpId());
            restaurantVoteResult.setRestaurantDetails(restaurantDetails);
        }
        return restaurantVoteResultList;
    }

}
