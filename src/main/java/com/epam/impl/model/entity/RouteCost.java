package com.epam.impl.model.entity;

import com.epam.impl.model.RouteInterface;

public class RouteCost implements RouteInterface {

    private String pointFrom;
    private String pointTo;
    private int length;
    private int cost;

    public RouteCost(String pointFrom, String pointTo, int length, int cost) {
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
}
