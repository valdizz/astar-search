package com.epam.impl.model;

public class Route {

    private String pointFrom;
    private String pointTo;
    private int length;
    private int cost;

    public Route(String pointFrom, String pointTo, int length, int cost) {
        this.pointFrom = pointFrom;
        this.pointTo = pointTo;
        this.length = length;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Route{" + pointFrom + ", " + pointTo + ", " + length + ", " + cost + '}';
    }
}
