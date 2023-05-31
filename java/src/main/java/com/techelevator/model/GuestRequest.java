package com.techelevator.model;

import java.util.List;

public class GuestRequest {
    private int eventId;
    private List<String> emails;

    public GuestRequest(int eventId, List<String> emails) {
        this.eventId = eventId;
        this.emails = emails;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "GuestRequest{" +
                "eventId=" + eventId +
                ", emails=" + emails +
                '}';
    }
}
