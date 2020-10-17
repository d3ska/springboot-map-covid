package com.example.springbootmapinit.parser;

import java.util.List;

public interface DataParser <T>{
    List<T> getListOfParsedData();
}
