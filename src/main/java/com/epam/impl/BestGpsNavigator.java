package com.epam.impl;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;
import com.epam.impl.exception.IncorrectFileFormatException;
import com.epam.impl.exception.SearchEngineException;
import com.epam.impl.model.RouteInterface;
import com.epam.impl.model.RouteRepository;
import com.epam.impl.searchengine.AStarSearchEngine;
import com.epam.impl.searchengine.CostInterface;

public class BestGpsNavigator<T extends RouteInterface> implements GpsNavigator {

    private RouteRepository<T> routeRepository;
    private CostInterface<T> costInterface;

    public BestGpsNavigator(Class<T> clazz, CostInterface<T> costInterface) {
        this.costInterface = costInterface;
        routeRepository = new RouteRepository<>(clazz);
    }

    @Override
    public void readData(String filePath) throws IncorrectFileFormatException, NumberFormatException {
        routeRepository.readDataFromFile(filePath);
    }

    @Override
    public Path findPath(String pointA, String pointB) throws SearchEngineException {
        return AStarSearchEngine.getBestPath(routeRepository.getData(), pointA, pointB, costInterface);
    }
}
