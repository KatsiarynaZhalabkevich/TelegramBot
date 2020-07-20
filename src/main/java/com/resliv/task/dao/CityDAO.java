package com.resliv.task.dao;

import com.resliv.task.entity.City;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

/**
 * Interface implements Spring Data CrudRepository
 */
public interface CityDAO extends CrudRepository<City, Long> {

    Optional<City> findCityByName(String name);
    void deleteCityByName(String name);

}
