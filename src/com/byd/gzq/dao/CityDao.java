package com.byd.gzq.dao;

import com.byd.gzq.bean.City;
import com.byd.gzq.utils.DBUtils;
import com.byd.gzq.utils.RedisUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.WebApplicationContextUtils;
import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Map;

public class CityDao {

    private static final Logger log = Logger.getLogger("gzq");

    /**
     * before each retrieval, try to query from redis, if not exist, then turn to mysql
     * @return
     */

    public City selectCityById(Integer id) {
        // first,try to retrieve from redis
        Connection conn = null;
        Jedis mq = RedisUtils.getRedisConn();
        Map<String, String> cityFieldsMap = mq.hgetAll("city:" + id);
        if(cityFieldsMap!=null&& !cityFieldsMap.isEmpty()){
            log.fatal("redis中找到了，不用通过MySQL进行查询。。。。");
            City city = new City();
            try {
                BeanUtils.populate(city,cityFieldsMap);
                return city;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        PreparedStatement pst =null;
        try {
            conn= DBUtils.getConnection();
            String sql = "select id,name,countrycode,district,population from city where id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            log.fatal("Redis中没找到，要通过MySQL进行查询。。。。");
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString(2);
                String countrycode = resultSet.getString(3);
                String district = resultSet.getString(4);
                int population = resultSet.getInt(5);
                City city = new City(id,name,countrycode,district,population);
                //cache to redis
                mq.hset("city:"+city.getId(),"id", String.valueOf(city.getId()));
                mq.hset("city:"+city.getId(),"name", city.getName());
                mq.hset("city:"+city.getId(),"countryCode", city.getCountryCode());
                mq.hset("city:"+city.getId(),"district", city.getDistrict());
                mq.hset("city:"+city.getId(),"population", String.valueOf(city.getPopulation()));
                return city;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            RedisUtils.closeRedisConn(mq);
            DBUtils.closeConnection(conn);
        }
        return null;
    }
}
