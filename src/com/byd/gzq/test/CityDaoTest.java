package com.byd.gzq.test;

import com.byd.gzq.bean.City;
import com.byd.gzq.dao.CityDao;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayInputStream;

public class CityDaoTest {

    final static Logger log = Logger.getLogger("CityDaoTest");
    @Test
    public void testCity(){
        CityDao cityDao = new CityDao();
        City city = cityDao.selectCityById(1);
        System.out.println(city);
        //-Dfile.encoding=UTF-8 -Duser.language=en -Duser.region=US
    }

    @Test
    public void testSpringCity(){
        log.fatal("zhou cong");
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        City city = ioc.getBean("city", City.class);
//        System.out.println(city);
    }

    @Before
    public void beforeOperation(){
        log.fatal("stO Before Orz");
    }
    @After
    public void afterOperation(){
        log.fatal("stO After Orz");
    }
}