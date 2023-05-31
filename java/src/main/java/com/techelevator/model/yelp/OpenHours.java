package com.techelevator.model.yelp;


/**
 * OpenHours contains the actual start and end times (military time) of the restaurant and whether it is open overnight.
 * Day 0-16 corresponds to days of the week; starting with Monday per international (ISO) standards
 * Each day of the week has an OpenHours, so a restaurant would have 7 objects of OpenHours (0-6)
 */
public class OpenHours {
    private boolean isOvernight;
    private String start;
    private String end;
    private int day;

    //getters and setters
    public boolean isOvernight() {
        return isOvernight;
    }

    public void setOvernight(boolean overnight) {
        isOvernight = overnight;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
