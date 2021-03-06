package com.byd.gzq.listener;

import com.byd.gzq.bean.City;
import com.byd.gzq.servlet.BaseServlet;
import com.byd.gzq.utils.DBUtils;
import com.byd.gzq.utils.RedisUtils;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class GlobalListener extends ContextLoaderListener{

    private static ApplicationContext ioc;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // after the initiation,there should be an spring application context in the tomcat
        ApplicationContext context =
                WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        ioc = context;

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while(drivers.hasMoreElements()){
                DriverManager.deregisterDriver(drivers.nextElement());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
//        DBUtils.closeDatasource();
//        RedisUtils.closeRedisConn(null);
        System.out.println("销毁。。。");
    }

    public static ApplicationContext getIoc() {
        return ioc;
    }
}
