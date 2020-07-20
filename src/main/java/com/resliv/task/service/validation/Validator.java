package com.resliv.task.service.validation;

import com.resliv.task.entity.City;

import java.util.List;
import java.util.stream.Collectors;

public class Validator {
    public static List<City> makeCityListValid(List<City> citiesFromBD, List<City> newCities) {
        List<String> citiesNames = citiesFromBD.stream().map(City::getName).collect(Collectors.toList());
        newCities.removeIf(c -> citiesNames.contains(c.getName()));
        return newCities;
    }
}
