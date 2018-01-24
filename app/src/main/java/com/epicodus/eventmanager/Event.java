package com.epicodus.eventmanager;

public class Event {
    private String eventID;
    private String description;
    private String type;
//    private String date;
//    private String time;
//    private String address;
//    private String link;
//    private String imageURL;
//    private String category;

    public Event(){

    }

    public Event(String eventID, String description, String type) {
        //(String name, String date, String time, String address, String link, String imageURL, String category)
        this.eventID = eventID;
        this.description = description;
        this.type = type;
//        this.date = date;
//        this.time = time;
//        this.address = address;
//        this.link = link;
//        this.imageURL = imageURL;
//        this.category = category;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
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
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
}
