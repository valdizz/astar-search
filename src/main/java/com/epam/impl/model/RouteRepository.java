package com.epam.impl.model;

import com.epam.impl.exception.IncorrectFileFormatException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RouteRepository implements Repository {

    private List<Route> routes;

    public RouteRepository() {
        routes = new ArrayList<>();
    }

    @Override
    public void readDataFromFile(String filePath) throws IncorrectFileFormatException, NumberFormatException {
        routes.clear();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){
            String line;
            while ((line = reader.readLine())!= null){
                String[] words = line.split("\\s");
                if (words.length != 4) {
                    throw new IncorrectFileFormatException("Incorrect file format!");
                }
                int length = Integer.valueOf(words[2]);
                int cost = Integer.valueOf(words[3]);
                routes.add(new Route(words[0], words[1], length, cost));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Route> getData() {
        return routes;
    }
}
