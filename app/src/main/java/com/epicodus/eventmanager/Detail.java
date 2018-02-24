package com.epicodus.eventmanager;

import org.parceler.Parcel;

@Parcel
public class Detail {
    String detailID;
    String detailText;
    int eventRating;

    public Detail() {}

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
