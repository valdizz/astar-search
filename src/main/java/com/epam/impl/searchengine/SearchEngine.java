package com.epam.impl.searchengine;

import com.epam.api.Path;
import com.epam.impl.exception.SearchEngineException;

public interface SearchEngine {

    Path getBestPath() throws SearchEngineException;
}
