package com.example.inthujan.finalproject;

public class RowItem {
    private String busName;
    private int picId;
    private String route;
    private String time;

    public RowItem(String busName, int picId, String route, String time) {
        this.busName = busName;
        this.picId = picId;
        this.route = route;
        this.time = time;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
