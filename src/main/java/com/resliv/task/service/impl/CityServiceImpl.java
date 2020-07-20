package com.resliv.task.service.impl;

import com.resliv.task.dao.CityDAO;
import com.resliv.task.entity.City;
import com.resliv.task.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Business Logic layer.
 */

@Service
@Transactional
public class CityServiceImpl implements CityService {
    private static final Logger logger = LogManager.getLogger(CityServiceImpl.class);
    private final CityDAO dao;

    @Autowired
    public CityServiceImpl(CityDAO dao) {
        this.dao = dao;
    }

    @Override
    public City addOneCity(City city) {
        City cityFromBD = getCityByName(city.getName()).orElse(null);
        return cityFromBD == null ? dao.save(city) : null;
    }

    @Override
    public List<City> addCities(List<City> cities) {
        List<City> citiesFromBD = (List<City>) dao.findAll();
        cities.removeAll(citiesFromBD);
        return (List<City>) dao.saveAll(cities);
    }

    @Override
    public Optional<City> getCityById(long id) {
        return dao.findById(id);
    }

    @Override
    public Optional<City> getCityByName(String name) {
        return dao.findCityByName(name);
    }

    @Override
    public List<City> getAllCities() {
        return (List<City>) dao.findAll();
    }

    @Override
    public City updateCity(City city) {
        return addOneCity(city);
    }

    @Override
    public void deleteCityById(long id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteCityByName(String name) {
        dao.deleteCityByName(name);
    }

    @Override
    public void deleteAllCities() {
        dao.deleteAll();
    }

    @Override
    public void deleteCity(City city) {
        dao.delete(city);
    }
}
