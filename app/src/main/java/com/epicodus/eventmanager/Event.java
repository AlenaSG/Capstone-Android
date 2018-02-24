package com.epicodus.eventmanager;

import org.parceler.Parcel;

@Parcel
public class Event {
    String eventID;
    String name; //was description
    String type;

    String date;
    String time;
    String address;
    //String link;
    String pushID;
    //String imageURL;


    public Event(){}

    public Event(String eventID, String name, String type, String date, String time, String address) {
        //(String name, String date, String time, String address, String link, String imageURL)
        this.eventID = eventID;
        this.name = name;
        this.type = type;
        this.date = date;
        this.time = time;
        this.address = address;
        //    this.link = link;
//        this.imageURL = imageURL;

    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPushID() {
        return pushID;
    }

    public void setPushID(String pushID) {
        this.pushID = pushID;
    }
//
//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public String getImageURL() {
//        return imageURL;
//    }
//
//    public void setImageURL(String imageURL) {
//        this.imageURL = imageURL;
//    }
//
//
}
