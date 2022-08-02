package com.byd.gzq.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
    private static JedisPoolConfig jpc;
    private static JedisPool jp;
    static{
        jpc = new JedisPoolConfig();
        jpc.setMaxIdle(10);
        String host = "localhost";
        int port = 6379;
        jp =  new JedisPool(jpc,host,port);
    }
    public static Jedis getRedisConn(){
        return jp.getResource();
    }

    public static void closeRedisConn(Jedis j){

    }
}
