package com.techelevator.controller;

import com.techelevator.model.RestaurantVoteResult;
import com.techelevator.model.VoteRequest;
import com.techelevator.service.VotingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vote")
@CrossOrigin
public class VoteController {

    VotingService vote;

    public VoteController(VotingService vote) {
        this.vote = vote;
    }


    /**
     * Handles the post request for voting
     * @param voteRequest
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (method = RequestMethod.POST)
    public void vote (@RequestBody VoteRequest voteRequest) {
        vote.recordVote(voteRequest.getEventId(), voteRequest.getGuestEmail(), voteRequest.getYelpRestaurantId(),voteRequest.isVote());
    }

    /**
     * Handles the post request for voting
     * @param voteRequest
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (method = RequestMethod.POST, path = "/comments")
    public void comments (@RequestBody VoteRequest voteRequest) {
        vote.recordComment(voteRequest.getEventId(), voteRequest.getGuestEmail(), voteRequest.getYelpRestaurantId(),voteRequest.getGuestComments());
    }

    /**
     * Given an event id we will pull back the finalists in order of the most votes to lowest
     * @param eventID
     */
    @RequestMapping (method= RequestMethod.GET, path = "/{eventID}/finalists")
    public List<RestaurantVoteResult> restaurantFinalists (@PathVariable int eventID) {
        return vote.getFinalists(eventID);
    }

}
