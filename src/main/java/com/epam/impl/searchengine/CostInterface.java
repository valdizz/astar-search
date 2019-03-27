package com.epam.impl.searchengine;

@FunctionalInterface
public interface CostInterface<T> {

    int getCost(T obj);
}
