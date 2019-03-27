package com.epam.impl.searchengine;

import com.epam.api.Path;
import com.epam.impl.exception.SearchEngineException;
import com.epam.impl.model.RouteInterface;

import java.util.*;

public class AStarSearchEngine{

    public static <T extends RouteInterface> Path getBestPath(List<T> routes, String pointA, String pointB, CostInterface<T> costInterface) throws SearchEngineException {
        if (routes == null || routes.isEmpty()) {
            throw new SearchEngineException("No search data!");
        }
        if (costInterface == null) {
            throw new SearchEngineException("No cost calculation function!");
        }

        //set of all points
        Set<String> points = new HashSet<>();
        //map of all neighbors with cost
        Map<String, Map<String, Integer>> neighbors = new HashMap<>();
        //fill set and map
        Map<String, Integer> neighborsMap;
        for (T route : routes) {
            points.add(route.getPointFrom());
            points.add(route.getPointTo());

            if (neighbors.get(route.getPointFrom()) != null)
                neighborsMap = neighbors.get(route.getPointFrom());
            else
                neighborsMap = new HashMap<>();

            neighborsMap.put(route.getPointTo(), costInterface.getCost(route));
            neighbors.put(route.getPointFrom(), neighborsMap);
        }

        //set of points already evaluated
        Set<String> closedSet = new HashSet<>();
        //set of currently discovered points
        Set<String> openSet = new HashSet<>();
        openSet.add(pointA);
        //save most efficient previous step
        Map<String, String> cameFrom = new HashMap<>();
        //cost from start point
        Map<String, Integer> cost = new HashMap<>();
        for (String point : points) {
            cost.put(point, Integer.MAX_VALUE);
        }
        cost.put(pointA, 0);
        //current point = start point
        String current = pointA;
        Integer currentScore = 0;

        while (!openSet.isEmpty()) {
            currentScore = Integer.MAX_VALUE;
            for (String entry : openSet) {
                if (cost.get(entry) <= currentScore) {
                    current = entry;
                    currentScore = cost.get(entry);
                }
            }

            //path was found
            if (current.equals(pointB)) {
                List<String> totalPath = new ArrayList<>();
                totalPath.add(current);
                int totalCost = cost.get(current);
                while (cameFrom.containsKey(current)) {
                    current = cameFrom.get(current);
                    totalPath.add(current);
                }
                Collections.reverse(totalPath);
                return new Path(totalPath, totalCost);
            }

            openSet.remove(current);
            closedSet.add(current);

            if (neighbors.get(current)==null)
                continue;

            //process the neighbors of the current point
            for (String neighbor : neighbors.get(current).keySet()) {
                if (closedSet.contains(neighbor))
                    continue;

                Integer tentativeCost = cost.get(current) + neighbors.get(current).get(neighbor);

                if (!openSet.contains(neighbor))
                    openSet.add(neighbor);
                else if (cost.get(neighbor) != Integer.MAX_VALUE && tentativeCost >= cost.get(neighbor))
                    continue;

                cameFrom.put(neighbor, current);
                cost.put(neighbor, tentativeCost);
            }
        }

        //path not found
        throw new SearchEngineException("Path not found!");
    }

}
