package com.example.springbootmapinit.model;

public class Point {

    private double lat;
    private double lon;
    private String value;

    public Point(double lat, double lon, String value) {
        this.lat = lat;
        this.lon = lon;
        this.value = value;
    }

    public Point() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
