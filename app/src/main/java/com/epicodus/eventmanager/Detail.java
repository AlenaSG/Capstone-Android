package com.epicodus.eventmanager;

/**
 * Created by alenagolovina on 1/25/18.
 */

public class Detail {
    private String detailID;
    private String detailText;
    private int eventRating;

    public Detail() {

    }

    public Detail(String detailID, String detailText, int eventRating) {
        this.detailID = detailID;
        this.detailText = detailText;
        this.eventRating = eventRating;
    }

    public String getDetailID() {
        return detailID;
    }

    public String getDetailText() {
        return detailText;
    }

    public int getEventRating() {
        return eventRating;
    }
}
