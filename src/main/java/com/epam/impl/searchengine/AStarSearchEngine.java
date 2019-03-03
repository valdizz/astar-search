package com.epam.impl.searchengine;

import com.epam.api.Path;
import com.epam.impl.exception.SearchEngineException;
import com.epam.impl.model.Route;

import java.util.*;

public class AStarSearchEngine implements SearchEngine{

    private List<Route> routes;
    private String pointA;
    private String pointB;

    public AStarSearchEngine(List<Route> routes, String pointA, String pointB) {
        this.routes = routes;
        this.pointA = pointA;
        this.pointB = pointB;
    }

    @Override
    public Path getBestPath() throws SearchEngineException {
        if (routes == null || routes.isEmpty()) {
            throw new SearchEngineException("No search data!");
        }

        //set of all points
        Set<String> points = new HashSet<>();
        //map of all neighbors with cost
        Map<String, Map<String, Integer>> neighbors = new HashMap<>();
        //fill set and map
        Map<String, Integer> neighborsMap;
        for (Route route : routes) {
            points.add(route.getPointFrom());
            points.add(route.getPointTo());

            if (neighbors.get(route.getPointFrom()) != null)
                neighborsMap = neighbors.get(route.getPointFrom());
            else
                neighborsMap = new HashMap<>();
            neighborsMap.put(route.getPointTo(), route.getLength() * route.getCost());
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
                List<String> reverseTotalPath = new ArrayList<>();
                reverseTotalPath.add(current);
                int totalCost = cost.get(current);
                while (cameFrom.containsKey(current)) {
                    current = cameFrom.get(current);
                    reverseTotalPath.add(current);
                }
                List<String> totalPath = new ArrayList<>();
                for (int i = reverseTotalPath.size()-1; i >= 0; i--) {
                    totalPath.add(reverseTotalPath.get(i));
                }
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
