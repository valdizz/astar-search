package com.epam.impl.model;

import com.epam.impl.exception.IncorrectFileFormatException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class RouteRepository<T> {

    private List<T> routes;
    private Class<T> clazz;

    public RouteRepository(Class<T> clazz) {
        routes = new ArrayList<>();
        this.clazz = clazz;
    }

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
                try {
                    T routeHolder = clazz.getConstructor(String.class, String.class, int.class, int.class).newInstance(words[0], words[1], length, cost);
                    routes.add(routeHolder);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> getData() {
        return routes;
    }
}
