package com.techelevator.service;

import com.techelevator.model.RestaurantVoteResult;

import java.util.List;

public interface VotingService {
    void recordVote (int eventId, String guestEmail, String restaurantId, boolean vote);
    void recordComment (int eventId, String guestEmail, String restaurantId, String guestComments);
    List<RestaurantVoteResult> getFinalists(int eventId);


}
