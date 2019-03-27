package com.epam.impl.model.entity;

import com.epam.impl.model.RouteInterface;

public class RouteTime implements RouteInterface {

    private String pointFrom;
    private String pointTo;
    private int length;
    private int time;

    public RouteTime(String pointFrom, String pointTo, int length, int time) {
        this.pointFrom = pointFrom;
        this.pointTo = pointTo;
        this.length = length;
        this.time = time;
    }

    public String getPointFrom() {
        return pointFrom;
    }

    public String getPointTo() {
        return pointTo;
    }

    public int getLength() {
        return length;
    }

    public int getTime() {
        return time;
    }
}
