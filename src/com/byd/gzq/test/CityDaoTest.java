package com.byd.gzq.test;

import com.byd.gzq.bean.City;
import com.byd.gzq.bean.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class CityDaoTest {

    final static Logger log = LogManager.getLogger(CityDaoTest.class);
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
    public void test002() throws IOException {
//        Person person = ioc.getBean(Person.class);
//        System.out.println(person.calNameLength());


        /**
         * System.out.println(person.getClass());
         * the result is like Person$$EnhancerBySpringCGLIB$$4c81b9f0
         * if a method of a class is proxied by spring aop,then the
         */

//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("1.txt"));
//        oos.writeObject(person);
//        oos.flush();
//        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("1.txt"));
        Person person1 = null;
        try {
            person1 = (Person) ois.readObject();
            System.out.println(person1.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchCity() throws InvocationTargetException, IllegalAccessException {
//        Jedis r = RedisUtils.getRedisConn();
//        Map<String, String> adminPersist = r.hgetAll("city:1");
//        City city = new City();
//        BeanUtils.populate(city,adminPersist);
//        System.out.println(city);
    }

    @Test
    public void afterOperation(){
        log.error(1);
    }
}