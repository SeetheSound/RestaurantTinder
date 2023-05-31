package com.techelevator.dao;

import com.techelevator.model.Guest;

import java.util.List;

public interface GuestEventDao {
    void createGuestEvent(int eventId, List<String> guestEmail);

    List<Guest> getGuest(int eventId);

    int findGuestID(int eventId, String guestEmail);


}
