package com.resliv.task.service;

import com.resliv.task.entity.City;

import java.util.List;
import java.util.Optional;


public interface CityService {
    City addOneCity(City city);

    List<City> addCities(List<City> cities);

    Optional<City> getCityById(long id);

    Optional<City> getCityByName(String name);

    List<City> getAllCities();

    City updateCity(City city);

    void deleteCityById(long id);

    void deleteCityByName(String name);

    void deleteAllCities();

    void deleteCity(City city);

}
