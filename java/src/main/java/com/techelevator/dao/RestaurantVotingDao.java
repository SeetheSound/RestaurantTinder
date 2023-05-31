package com.techelevator.dao;

import com.techelevator.model.RestaurantVoteResult;
import com.techelevator.model.UserComment;

import java.util.List;

public interface RestaurantVotingDao {
     void recordVote (int eventId, int guestId, String restaurantId, boolean vote);

     void recordComment(int eventId, int guestId, String restaurantId, String comment);

     List<RestaurantVoteResult> getFinalists(int eventId);

     List<UserComment> getComments(int eventId);

}
