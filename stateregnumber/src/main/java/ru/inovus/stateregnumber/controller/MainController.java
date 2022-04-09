package ru.inovus.stateregnumber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inovus.stateregnumber.interfaces.servise.GrateGosNumber;

@RestController
@RequestMapping(path = "/number")
public class MainController {

    @Autowired
    private GrateGosNumber grateGosNumber;


    @GetMapping(path= "/next")
    public String nextSeries() {
        return grateGosNumber.nextSeries();
    }

    @GetMapping(path= "/random")
    public String randomSeries()  {
        return grateGosNumber.randomSeries();
    }

}
