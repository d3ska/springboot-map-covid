package com.example.springbootmapinit.parser;

import com.example.springbootmapinit.reader.DataReader;
import com.example.springbootmapinit.model.Point;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidParser implements DataParser<Point> {

    private DataReader dataReader;
    private List<Point> points;

    @Autowired
    public CovidParser(DataReader dataReader) {
        this.dataReader = dataReader;
        points = new ArrayList<>();
    }

    @Override
    public List<Point> getListOfParsedData() {
        try {
            CSVParser csvRecords = parseCovidData();
            addPointsToList(csvRecords);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    private CSVParser parseCovidData() throws IOException {
        StringReader stringReader = dataReader.getDate();
        CSVParser parse = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);
        return parse;
    }

    private void addPointsToList(CSVParser csvRecords) {
        for (CSVRecord strings : csvRecords) {
            double lat = Double.parseDouble(strings.get("Lat"));
            double lon = Double.parseDouble(strings.get("Long"));
            String infectedSoFar = strings.get(getCurrentDate());
            points.add(new Point(lat, lon, infectedSoFar));
        }
    }

    private static String getCurrentDate() {
        int databaseUpdateDelay = 2;
        if(dataIsUpdated())
            databaseUpdateDelay = 1;
        String date = formatDate(databaseUpdateDelay);
        return date;
    }

    private static boolean dataIsUpdated() {
        return LocalTime.now().isAfter(LocalTime.of(9, 0));
    }

    private static String formatDate(int databaseUpdateDelay) {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String date = LocalDate.now().minusDays(databaseUpdateDelay).format(formater);
        date = date.substring(0, 8);
        date = date.replace("-", "/");
        return date;
    }
}



