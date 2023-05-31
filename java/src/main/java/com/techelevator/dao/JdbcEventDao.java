package com.techelevator.dao;

import com.techelevator.model.EventRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao{
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcEventDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public int createEvent(EventRequest eventRequest, String username) {
        String sql = "INSERT INTO event (host_user_id, event_name, event_description, event_date, rsvp_deadline, location) values (?, ?, ?, ?, ?, ?) RETURNING event_id;";
        int userId = userDao.findIdByUsername(username);
        int eventId = jdbcTemplate.queryForObject(sql, new Object[]{userId, eventRequest.getEventName(), eventRequest.getDescription(), Timestamp.valueOf(eventRequest.getEventDate()), Timestamp.valueOf(eventRequest.getRsvpDeadline()), eventRequest.getLocation()}, Integer.class);
        return eventId;
    }

    @Override
    public EventRequest getEvent(int eventId) {
        String sql = "SELECT * FROM event WHERE event_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, eventId);
        EventRequest eventRequest = mapRowToEventRequest(result);
        return eventRequest;
    }


    //update the mapRowevent
    private EventRequest mapRowToEventRequest(SqlRowSet rs) {
        EventRequest eventRequest = new EventRequest();
        while(rs.next()) {
            eventRequest.setEventName(rs.getString("event_name"));
            eventRequest.setDescription(rs.getString("event_description"));
            eventRequest.setEventDate(getLocalDateTime(rs.getTimestamp("event_date")));
            eventRequest.setRsvpDeadline(getLocalDateTime(rs.getTimestamp("rsvp_deadline")));
            eventRequest.setHostUserId(rs.getInt("host_user_id"));
            eventRequest.setEventId(rs.getInt("event_id"));
            eventRequest.setLocation(rs.getString("location"));
            eventRequest.setFinalSelectionRestaurantId(rs.getString("final_selection_restaurant_id"));
        }
        return eventRequest;
    }

    /**
     * Query for the rsvpDeadline from the event table
     * @param eventId
     * @return
     */
    @Override
    public LocalDateTime getRsvpDeadline(int eventId) {
        String sql = "SELECT rsvp_deadline FROM event where event_id = ?";
        LocalDateTime dateTime = jdbcTemplate.queryForObject(sql, LocalDateTime.class, eventId);
        return dateTime;
    }

    @Override
    public void finalRestaurantSelectionId(int eventId, String yelpRestaurantId) {
        String sql = "UPDATE event set final_selection_restaurant_id = ?  WHERE event_id =?";
        jdbcTemplate.update(sql, yelpRestaurantId, eventId);
    }

    @Override
    public List<EventRequest> getEvents(String username) {
        int userId = userDao.findIdByUsername(username);
        String sql = "select * from event where host_user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        return mapRowToEventRequests(result);


    }

    private List<EventRequest> mapRowToEventRequests(SqlRowSet rs) {
        List<EventRequest> eventRequestList = new ArrayList<>();
        while(rs.next()) {
            EventRequest eventRequest = new EventRequest();
            eventRequest.setEventName(rs.getString("event_name"));
            eventRequest.setDescription(rs.getString("event_description"));
            eventRequest.setEventDate(getLocalDateTime(rs.getTimestamp("event_date")));
            eventRequest.setRsvpDeadline(getLocalDateTime(rs.getTimestamp("rsvp_deadline")));
            eventRequest.setHostUserId(rs.getInt("host_user_id"));
            eventRequest.setEventId(rs.getInt("event_id"));
            eventRequest.setLocation(rs.getString("location"));
            eventRequest.setFinalSelectionRestaurantId(rs.getString("final_selection_restaurant_id"));
            eventRequestList.add(eventRequest);
        }
        return eventRequestList;
    }

    public LocalDateTime getLocalDateTime(Timestamp timestamp) {
        if(timestamp!= null){
            return timestamp.toLocalDateTime();
        } else {
            return null;
        }
    }
}
