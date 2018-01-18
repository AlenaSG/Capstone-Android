package com.epicodus.eventmanager;

public class Event {
    private String name;
    private String date;
    private String time;
    private String address;
    private String link;
    private String imageURL;
    private String category;

    public Event(String name, String date, String time, String address, String link, String imageURL, String category) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.address = address;
        this.link = link;
        this.imageURL = imageURL;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
