package com.techelevator.model.yelp;

import java.util.List;

/**
 * Hours has an hoursType "regular" (assuming this means not holiday), isOpenNow if it is open currently,
 * and List OpenHours which is the open hours for the restaurant for ALL days of the week;
 * it is a list of the OpenHours class, so it can hold the hours for each day since OpenHours returns an individual day's hours, this will
 * show multiple hours.
 */

public class Hours {
    private List<OpenHours> open;
    private String hoursType;
    private boolean isOpenNow;


    //Getters and setters
    public List<OpenHours> getOpen() {
        return open;
    }

    public void setOpen(List<OpenHours> open) {
        this.open = open;
    }

    public String getHoursType() {
        return hoursType;
    }

    public void setHoursType(String hoursType) {
        this.hoursType = hoursType;
    }

    public boolean isOpenNow() {
        return isOpenNow;
    }

    public void setOpenNow(boolean openNow) {
        isOpenNow = openNow;
    }
}
