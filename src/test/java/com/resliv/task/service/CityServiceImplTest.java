package com.resliv.task.service;

import com.resliv.task.bot.Bot;
import com.resliv.task.dao.CityDAO;
import com.resliv.task.entity.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceImplTest {
    private City city;
    private List<City> citiesLst;
    @MockBean
    private CityDAO dao;
    @MockBean
    private Bot bot;
    @Autowired
    private CityService service;

    @Before
    public void init() {
        city = new City(1L, "Минск", "Чистый и безопасный город");
        citiesLst = Arrays.asList(
                new City("Брест", "Посетите брестскую крепость"),
                new City("Пекин", "Попробуйте утку"),
                new City("Барселона", "Архитектура Гауди вас покорит"),
                new City("Слуцк", "Отличное место для шопинга! Купите себе слуцкий пояс!"));
    }

    @Test
    public void addOneCityTest() {
        Mockito.when(dao.save(city)).thenReturn(city);
        City testCity = service.addOneCity(city);
        Assert.assertNotNull(testCity);
        Assert.assertEquals(city, testCity);

    }

    @Test
    public void addCities() {
        Mockito.when(dao.saveAll(citiesLst)).thenReturn(citiesLst);
       List<City> savedCitiesLst = service.addCities(citiesLst);
        Assert.assertEquals(citiesLst.size(), savedCitiesLst.size());
    }

    @Test
    public void getCityByIdTest() {
        Long id = 1L;
        Mockito.when(dao.findById(id)).thenReturn(java.util.Optional.ofNullable(city));
        city = service.getCityById(id).orElse(null);
        Assert.assertEquals(id, city.getId());

    }

    @Test
    public void getCityByNameTest() {
        String name = "Минск";
        Mockito.when(dao.findCityByName(name)).thenReturn(java.util.Optional.ofNullable(city));
        city = service.getCityByName(name).orElse(null);
        Assert.assertEquals(name, city.getName());
    }

    @Test
    public void deleteCityByNameTest() {
        String name ="Минск";
        service.deleteCityByName(name);
        Mockito.verify(dao, times(1)).deleteCityByName(name);
    }

}
