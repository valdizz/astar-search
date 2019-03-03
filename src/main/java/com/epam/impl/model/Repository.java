package com.epam.impl.model;

import com.epam.impl.exception.IncorrectFileFormatException;

import java.util.List;

public interface Repository {

    void readDataFromFile(String filePath) throws IncorrectFileFormatException;

    List<Route> getData();
}
