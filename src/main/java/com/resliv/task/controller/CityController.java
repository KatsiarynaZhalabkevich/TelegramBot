package com.resliv.task.controller;

import com.resliv.task.entity.City;
import com.resliv.task.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Method to manage the app via REST API
 */
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService service;

    @Autowired
    public CityController(CityService cityService) {
        this.service = cityService;
    }

    @PostMapping("/city")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City newCity = service.addOneCity(city);
        return newCity != null ? new ResponseEntity<>(newCity, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/cities")
    public ResponseEntity<List<City>> addSeveralCities(@RequestBody List<City> cities) {
        List<City> newCitiesList = service.addCities(cities);
        return newCitiesList != null ? new ResponseEntity<>(newCitiesList, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Long id) {
        City city = service.getCityById(id).orElse(null);
        return city != null ? new ResponseEntity<>(city, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/city/{name}")
    public ResponseEntity<City> getCityByName(@PathVariable("name") String name) {
        System.out.println(name);
        City city = service.getCityByName(name).orElse(null);
        return city != null ? new ResponseEntity<>(city, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = service.getAllCities();
        return cities != null ? new ResponseEntity<>(cities, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(
            @PathVariable Long id,
            @RequestBody City newCity) {
        City city = service.getCityById(id).orElse(null);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setName(newCity.getName());
        city.setInfo(newCity.getInfo());
        newCity = service.updateCity(city);

        return newCity != null ? new ResponseEntity<>(newCity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCityById(@PathVariable Long id) {
        service.deleteCityById(id);
    }

    @DeleteMapping("/city/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCityByName(@PathVariable("name") String name) {
        service.deleteCityByName(name);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllCities() {
        service.deleteAllCities();
    }

}
