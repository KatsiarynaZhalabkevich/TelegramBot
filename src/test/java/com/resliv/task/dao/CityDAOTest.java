package com.resliv.task.dao;


import com.resliv.task.bot.Bot;
import com.resliv.task.entity.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test class for DAO layer. Only some methods are tested as it is Spring Data realization
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value={"/create-city-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value={"/create-city-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CityDAOTest {
    @Autowired
    private CityDAO dao;
    @MockBean
    private Bot bot;

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

    @Test(expected = DataIntegrityViolationException.class)
    public void addNotUniqCityTest(){
        City city = new City("Барселона", "Архитектура Гауди вас покорит");
        dao.save(city);

    }


}
