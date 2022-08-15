package com.byd.gzq.test;

import com.byd.gzq.bean.City;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class CityDaoTest {

    final ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");;
    final static Logger log = Logger.getLogger("CityDaoTest");
//    @Test
//    public void testCity(){
//        CityDao cityDao = new CityDao(null);
//        City city = cityDao.selectCityById(1);
//        System.out.println(city);
//        //-Dfile.encoding=UTF-8 -Duser.language=en -Duser.region=US
//    }

    @Test
    public void testSpringCity(){
        log.fatal("zhou cong");
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        City city = ioc.getBean("city", City.class);
        log.info(city);
    }

    @Test
    public void test002(){
        Object person = ioc.getBean("person");
        System.out.println(person);
    }

    @Test
    public void fetchCity() throws InvocationTargetException, IllegalAccessException {
//        Jedis r = RedisUtils.getRedisConn();
//        Map<String, String> adminPersist = r.hgetAll("city:1");
//        City city = new City();
//        BeanUtils.populate(city,adminPersist);
//        System.out.println(city);
    }

    @After
    public void afterOperation(){
        log.fatal("stO After Orz");
    }
}