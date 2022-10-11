package com.byd.gzq.test;

import com.alibaba.dubbo.rpc.filter.EchoFilter;
import com.byd.gzq.bean.*;
import com.byd.gzq.dao.PersonMapper;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class CityDaoTest {

    private ApplicationContext ioc;

    @Before
    public void setIOC(){
        ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
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
        Person person = ioc.getBean("person", Person.class);
        int i = person.calNameLength();
        log.error(person.replaced());
    }

    @Test
    public void testStaticfactory() throws Exception {
        Object a1 = ioc.getBean("personFactoryBean");
        Object a2 = ioc.getBean("&personFactoryBean");
        System.out.println(a1);
        System.out.println(a2);
    }


    @Test
    public void testMethodInjection(){
//        PersonC personC = ioc.getBean("personC", PersonC.class);
//        System.out.println(personC.getPerson().getName());
//        System.out.println(personC.getPerson().getName());

        Person p = ioc.getBean("person", Person.class);
        log.warn(p.replaced());
    }

    @Test
    public void testPostProcessor(){
        WithDate bean = ioc.getBean("withDate",WithDate.class);
        WithDate erhousheng = ioc.getBean("erhousheng", WithDate.class);
        log.info(erhousheng);
        log.info(bean);
    }

    @Test
    public void testIbatis(){
        PersonMapper personMapper = ioc.getBean("personMapper", PersonMapper.class);
        for (Person p : personMapper.selectPersons()) {
            System.out.println(p);
        }
    }

    @Test
    public void testIbatisInsert(){
        PersonMapper personMapper = ioc.getBean("personMapper",PersonMapper.class);
        for (int i = 1; i <= 10000; i++) {
            String name = "_#$"+(int)(Math.random()*1000000);
            int age = (int)(Math.random()*100);
            Person person = new Person(name, i, name, age);
            personMapper.insertPerson(person);
        }
    }


//    @Test
//    public void testOOS(){
//        Person person = ioc.getBean("person", Person.class);
//        System.out.println(person.getName());
//        try {
//            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("D:\\Person.dat"));
//            obj.writeObject(person);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testOIS(){
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\Person.dat"));
//            Person person = (Person) ois.readObject();
//            System.out.println(person);
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void testStudent(){
        Student stu = new Student();
        stu.setName("erhousheng");
        stu.setAge(65);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\Person.dat"));
            oos.writeObject(stu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testStudentRead(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\Person.dat"));
            Student student = (Student) ois.readObject();
            System.out.println(student);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSqlSessionFactory(){
        SqlSessionFactory sqlSessionFactory = ioc.getBean(SqlSessionFactory.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            Person person = mapper.selectPersonById(1998);
            System.out.println(person);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * test if {@link java.util.ConcurrentModificationException} will be
     * thrown if remove some element
     */
    @Test
    public void coward(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        Iterator<Integer> iter = integers.iterator();

        while(iter.hasNext()){
            Integer value = iter.next();
            if(value.equals(2)){
                log.error(value);
//                integers.remove(value);
                iter.remove();
            }
            System.out.println(value);
        }
    }

    @Test
    public void testQuotation(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate parse = LocalDate.parse("1998年04月28日", formatter);
        LocalDate now = LocalDate.now();
        String format = now.format(formatter);
        log.error(format);
        log.error(parse);
    }

    @Test
    public void translator(){
        try {
            String res = Translate.execute("this is not the case", Language.ENGLISH, Language.CHINESE_SIMPLIFIED);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}