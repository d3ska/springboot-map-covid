package com.example.springbootmapinit.reader;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;

@Service
public class CovidReader implements DataReader {


    private static final String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";


    @Override
    public StringReader getDate() {
        RestTemplate restTemplate = new RestTemplate();
        String values = restTemplate.getForObject(url, String.class);
        StringReader stringReader = new StringReader(values);

        return stringReader;
    }
}
