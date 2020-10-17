package com.example.springbootmapinit.controller;

import com.example.springbootmapinit.parser.CovidParser;
import com.example.springbootmapinit.parser.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
    private DataParser dataParser;

    @Autowired
    public MapController(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    @GetMapping
    public String getMap(Model model) {
        model.addAttribute("points", dataParser.getListOfParsedData());
        return "map";
    }

}
