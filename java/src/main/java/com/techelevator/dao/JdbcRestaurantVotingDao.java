package com.techelevator.dao;

import com.techelevator.model.RestaurantVoteResult;
import com.techelevator.model.UserComment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRestaurantVotingDao implements RestaurantVotingDao {


    private final JdbcTemplate jdbcTemplate;

    public JdbcRestaurantVotingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Inserts a vote into the database
     * @param eventId
     * @param guestId
     * @param restaurantId
     * @param vote
     *
     */
    @Override
    public void recordVote(int eventId, int guestId, String restaurantId, boolean vote) {
        String getId = "Select restaurant_votes_id from restaurant_voting where guest_event_id =? and event_id = ? and yelp_restaurant_id = ?";
        Integer voteId = null;
        try {
            voteId = jdbcTemplate.queryForObject(getId, Integer.class, guestId, eventId, restaurantId);
        } catch (EmptyResultDataAccessException e) {
            String insertVoteSql = "Insert into restaurant_voting (event_id, guest_event_id, yelp_restaurant_id, vote) values (?, ?, ?, ?)";
            jdbcTemplate.update(insertVoteSql, eventId, guestId, restaurantId, vote);
        }
        if(voteId != null){
            String updateVoteSql = "UPDATE restaurant_voting SET vote = ? WHERE restaurant_votes_id = ? ";
            jdbcTemplate.update(updateVoteSql, vote, voteId);
        }


    }

    @Override
    public void recordComment(int eventId, int guestId, String restaurantId, String comment) {
        String getId = "Select restaurant_votes_id from restaurant_voting where guest_event_id =? and event_id = ? and yelp_restaurant_id = ?";
        Integer voteId = null;
        try {
            voteId = jdbcTemplate.queryForObject(getId, Integer.class, guestId, eventId, restaurantId);
        } catch (EmptyResultDataAccessException e) {
            String insertVoteSql = "Insert into restaurant_voting (event_id, guest_event_id, yelp_restaurant_id, guest_comments) values (?, ?, ?, ?)";
            jdbcTemplate.update(insertVoteSql, eventId, guestId, restaurantId, comment);
        }
        if(voteId != null){
            String updateVoteSql = "UPDATE restaurant_voting SET guest_comments = ? WHERE restaurant_votes_id = ? ";
            jdbcTemplate.update(updateVoteSql, comment, voteId);
        }
    }


    /**
     * SQL query to retrieve restaurant ID and net vote count of each voted-on restaurant in a specific event, grouped by yelp_restaurant_id.
     * Uses CASE statement to count upvotes as +1 and downvotes as -1, and sums vote count for each restaurant to calculate net vote count
     * @param eventId
     * @return
     */
    @Override
    public List<RestaurantVoteResult> getFinalists(int eventId) {
        String query = "SELECT yelp_restaurant_id, " +
                " SUM(CASE WHEN vote THEN 1 ELSE -1 END) AS net_vote_count" +
                " FROM restaurant_voting WHERE event_id = ?" +
                " GROUP BY yelp_restaurant_id;";

        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(query, eventId);
        List<RestaurantVoteResult> results = new ArrayList<>();

        while (resultSet.next()) {
            String restaurantYelpId = resultSet.getString("yelp_restaurant_id");
            int netVoteCount = resultSet.getInt("net_vote_count");
            RestaurantVoteResult result = new RestaurantVoteResult(restaurantYelpId, netVoteCount);
            results.add(result);
        }

        return results;
    }

    @Override
    public List<UserComment> getComments(int eventId) {
        //TODO revisit once we finish the table with how guests are registered.
        String query = "Select  from restaurant_voting where event_id = ?";
        return null;
    }


}
