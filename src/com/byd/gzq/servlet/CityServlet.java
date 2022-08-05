package com.byd.gzq.servlet;

import com.byd.gzq.bean.City;
import com.byd.gzq.dao.CityDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@SuppressWarnings({"unused"})
public class CityServlet extends BaseServlet {

    private Object syncObj = new Object();
    private final CityDao cityDao = new CityDao();
    public void retrieveCity(HttpServletRequest req, HttpServletResponse resp) {
        synchronized (syncObj){
            while (!available) {
                try {
                    System.out.println("waiting" + Thread.currentThread().getName());
                    syncObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("woken-up" + Thread.currentThread().getName());
            available = false;
            action(req, resp);
        }
    }

    public void action(HttpServletRequest req, HttpServletResponse resp) {
        synchronized (syncObj) {
            String cid = req.getParameter("cid");
            City city = null;
//        MessageServlet o = (MessageServlet)MessageServlet.getMessage().toArray()[0];
//        System.out.println(o);

            if ((cid != null) && (!"".equals(cid))) {
                try {
                    int castCid = Integer.parseInt(cid);
                    city = cityDao.selectCityById(castCid);
                } catch (NumberFormatException e) {
                    city = null;
                }
            }
            if (city == null) {
                city = ioc.getBean("city", City.class);
            }

            req.setAttribute("city", city);
            HttpSession session = req.getSession();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cookie c = new Cookie("city" + city.getId(), city.getName());
            c.setDomain("localhost");
            c.setPath("/");
            resp.addCookie(c);
            try {
                resp.setContentType("text/html;charset=utf8");
                req.getRequestDispatcher("success.jsp").forward(req, resp);
                available = true;
                syncObj.notifyAll();
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
