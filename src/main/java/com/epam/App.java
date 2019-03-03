package com.epam;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;
import com.epam.impl.BestGpsNavigator;
import com.epam.impl.exception.IncorrectFileFormatException;
import com.epam.impl.exception.SearchEngineException;

/**
 * This class app demonstrates how your implementation of {@link com.epam.api.GpsNavigator} is intended to be used.
 */
public class App {

    public static void main(String[] args) throws IncorrectFileFormatException, SearchEngineException {
        final GpsNavigator navigator = new BestGpsNavigator();
        navigator.readData("D:\\Gps\\road_map2.ext");

        final Path path = navigator.findPath("F", "B");
        System.out.println(path);
    }
}
