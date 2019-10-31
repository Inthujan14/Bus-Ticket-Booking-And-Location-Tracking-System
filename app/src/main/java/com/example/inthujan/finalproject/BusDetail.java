package com.example.inthujan.finalproject;

public class BusDetail {
    public String bus_name;
    public String bus_number;
    public String bus_date;
    public String bus_from;
    public String bus_to;
    public String bus_condition;


    public BusDetail(String bus_name, String bus_number, String bus_date, String bus_from, String bus_to, String bus_condition) {
        this.bus_name = bus_name;
        this.bus_number = bus_number;
        this.bus_date = bus_date;
        this.bus_from = bus_from;
        this.bus_to = bus_to;
        this.bus_condition = bus_condition;
    }

    public BusDetail() {
    }
}
