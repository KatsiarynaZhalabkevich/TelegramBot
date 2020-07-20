package com.resliv.task.dao;


import com.resliv.task.bot.Bot;
import com.resliv.task.entity.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;

/**
 * Test class for DAO layer. Only 3 methods are tested as it is Spring Data realization
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityDAOTest {
    @Autowired
    private CityDAO dao;
    @MockBean
    private Bot bot;

    @Before
    public void init() {
        List<City> citiesLst = Arrays.asList(
                new City("Брест", "Посетите брестскую крепость"),
                new City("Пекин", "Попробуйте утку"),
                new City("Барселона", "Архитектура Гауди вас покорит"),
                new City("Слуцк", "Отличное место для шопинга! Купите себе слуцкий пояс!"));
        dao.saveAll(citiesLst);
    }

    @Test
    public void getCityByIdTest() {
        Long id = 1L;
        City city = dao.findById(id).orElse(null);
        Assert.assertNotNull(city);
        Assert.assertSame(id, city.getId());
    }

    @Test
    public void findCityByNameTest() {
        String name = "Брест";
        City city = dao.findCityByName(name).orElse(null);
        Assert.assertNotNull(city);
        Assert.assertEquals(name, city.getName());

    }

    @Test
    @Transactional
    @Rollback
    public void deleteCityByNameTest() {
        String name = "Брест";
        dao.deleteCityByName(name);
        City city = dao.findCityByName(name).orElse(null);
        Assert.assertNull(city);

    }

    @Test
    public void addNotUniqCityTest(){
        City city = new City("Барселона", "Архитектура Гауди вас покорит");
        dao.save(city);
    }


}
