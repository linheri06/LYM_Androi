package com.example.lym.Model;

public class Image {
    private String url;
    private String cap;
    private String date;

    public Image(){}

    public Image(String url, String cap, String date) {
        this.url = url;
        this.cap = cap;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
