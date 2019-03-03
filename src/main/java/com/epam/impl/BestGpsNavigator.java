package com.epam.impl;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;
import com.epam.impl.exception.IncorrectFileFormatException;
import com.epam.impl.exception.SearchEngineException;
import com.epam.impl.model.Repository;
import com.epam.impl.model.RouteRepository;
import com.epam.impl.searchengine.AStarSearchEngine;
import com.epam.impl.searchengine.SearchEngine;

public class BestGpsNavigator implements GpsNavigator {

    private Repository routeRepository;

    public BestGpsNavigator() {
        routeRepository = new RouteRepository();
    }

    @Override
    public void readData(String filePath) throws IncorrectFileFormatException, NumberFormatException {
        routeRepository.readDataFromFile(filePath);
    }

    @Override
    public Path findPath(String pointA, String pointB) throws SearchEngineException {
        SearchEngine searchEngine = new AStarSearchEngine(routeRepository.getData(), pointA, pointB);
        return searchEngine.getBestPath();
    }
}
