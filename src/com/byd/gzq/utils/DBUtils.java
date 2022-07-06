package com.byd.gzq.utils;

import org.apache.commons.dbcp.BasicDataSource;
import redis.clients.jedis.Jedis;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtils {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Jedis j = RedisUtils.getRedisConn();
                j.setex("cnt", 100, String.valueOf(Long.MAX_VALUE - 160));
                boolean flag = true;
                while (flag) {
                    try {
                        Connection connection = DBUtils.getConnection();
                        PreparedStatement shenzhen = connection.prepareStatement("select name from city where id=?");
                        long cnt = 161 - (Long.MAX_VALUE - Long.parseLong(j.get("cnt")));
                        shenzhen.setLong(1,1889+cnt);
                        ResultSet rs = shenzhen.executeQuery();
                        if(rs.next()){
                            System.out.println("success " + cnt+"\t"+rs.getString(1));
                        }
                        System.out.println(DBUtils.getActive());
                        Thread.sleep(500);
                        j.incr("cnt");
                    } catch (Throwable e) {
                        flag = false;
                        e.printStackTrace();
                        System.out.println(161-(Long.MAX_VALUE - Long.parseLong(j.get("cnt"))));
                    }
                }
            }
        }, "test_max_connection").start();
    }

    private static BasicDataSource basicDataSource;

    private static ResourceBundle resourceBundle;

    static{
        resourceBundle=ResourceBundle.getBundle("com.byd.gzq.utils.database");
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(resourceBundle.getString("mysql.driver"));
        basicDataSource.setDefaultAutoCommit(false);
        basicDataSource.setUrl(resourceBundle.getString("mysql.url"));
        basicDataSource.setUsername(resourceBundle.getString("mysql.user"));
        basicDataSource.setPassword(resourceBundle.getString("mysql.password"));
        basicDataSource.setMaxActive(10);
        basicDataSource.setMaxWait(5000);
    }
    public static Connection getConnection() {
        //OPTIMIZE 每次不是建立新连接，而是从池子里找，有则返回，无则创建
        try {
            Connection connection = basicDataSource.getConnection();
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getActive(){
        return basicDataSource.getNumActive();
    }
}
