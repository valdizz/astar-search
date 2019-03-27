package com.epam;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;
import com.epam.impl.BestGpsNavigator;
import com.epam.impl.exception.IncorrectFileFormatException;
import com.epam.impl.exception.SearchEngineException;
import com.epam.impl.model.entity.RouteCost;
import com.epam.impl.model.entity.RouteTime;
import com.epam.impl.searchengine.CostInterface;

/**
 * This class app demonstrates how your implementation of {@link com.epam.api.GpsNavigator} is intended to be used.
 */
public class App {

    public static void main(String[] args) throws IncorrectFileFormatException, SearchEngineException {

        GpsNavigator navigator;
        navigator= new BestGpsNavigator<>(RouteCost.class, obj -> obj.getLength()*obj.getCost());
//        navigator= new BestGpsNavigator<>(RouteCost.class, obj -> obj.getLength());
//        navigator = new BestGpsNavigator<>(RouteTime.class, obj -> obj.getLength()*obj.getTime()*2);

        navigator.readData("D:\\Gps\\road_map2.ext");

        final Path path = navigator.findPath("F", "B");
        System.out.println(path);
    }
}
