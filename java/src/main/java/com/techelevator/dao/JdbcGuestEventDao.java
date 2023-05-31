package com.techelevator.dao;

import com.techelevator.model.Guest;
import com.techelevator.model.RestaurantVoteResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGuestEventDao implements GuestEventDao {
    JdbcTemplate jdbcTemplate;

    public JdbcGuestEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void createGuestEvent(int eventId, List<String> guestEmail) {
        String sql = "INSERT INTO guest_event (email, event_id) values(?, ?);";
        for (String email : guestEmail) {
            try {
                jdbcTemplate.update(sql, email, eventId);
            } catch (DuplicateKeyException e) {
                // handle the exception here
                System.out.println("Duplicate email found: " + email);
            }
        }
    }

    @Override
    public List<Guest> getGuest(int eventId) {
        String sql = "Select * from guest_event where event_id=?";
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql, eventId);
        List<Guest> results = new ArrayList<>();

        while (resultSet.next()) {
            String guestEmail = resultSet.getString("email");
            int guestEventId = resultSet.getInt("guest_event_id");

            Guest result = new Guest(guestEventId, guestEmail, eventId);
            results.add(result);
        }
        return results;
    }

    @Override
    public int findGuestID(int eventId, String guestEmail) {
        String sql = "SELECT guest_event_id FROM guest_event WHERE email = ? AND event_id = ?";
        int guestEventId = jdbcTemplate.queryForObject(sql, Integer.class,  guestEmail, eventId);
        return guestEventId;
    }
}
