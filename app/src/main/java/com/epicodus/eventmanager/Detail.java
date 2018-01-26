package com.epicodus.eventmanager;

/**
 * Created by alenagolovina on 1/25/18.
 */

public class Detail {
    private String detailID;
    private String detailText;
    private int eventRaiting;

    public Detail() {

    }

    public Detail(String detailID, String detailText, int eventRaiting) {
        this.detailID = detailID;
        this.detailText = detailText;
        this.eventRaiting = eventRaiting;
    }

    public String getDetailID() {
        return detailID;
    }

    public String getDetailText() {
        return detailText;
    }

    public int getEventRaiting() {
        return eventRaiting;
    }
}
