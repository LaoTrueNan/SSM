package com.byd.gzq.servlet;

import com.byd.gzq.Customer;
import com.byd.gzq.bean.City;
import com.byd.gzq.dao.CityDao;
import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/message")
@SuppressWarnings({"unused"})
public class CityServlet extends BaseServlet{
    private static CopyOnWriteArraySet<CityServlet> message = new CopyOnWriteArraySet<>();
    private Session session = null;

    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        message.add(this);
    }

    @OnMessage
    public void onMessage(int count) throws IOException {
        message(count);
    }

    @OnError
    public void onError(Throwable e){
        System.out.println(e.getMessage());
    }

    @OnClose
    public void onClose(){
        message.remove(this);
    }


    public void message(int count) throws IOException {
        for (CityServlet cityServlet : message) {
            cityServlet.session.getBasicRemote().sendText(String.valueOf(count));
        }

    }

    public void retrieveCity(HttpServletRequest req, HttpServletResponse resp){
        String cid = req.getParameter("cid");
        City city = null;
        CityDao cityDao = new CityDao(this);
        if((cid!=null)&&(!"".equals(cid))){
            try {
                int castCid = Integer.parseInt(cid);
                city= cityDao.selectCityById(castCid);
            } catch (NumberFormatException e) {
                city=null;
            }
        }
        if(city==null){
            city = ioc.getBean("city",City.class);
        }

        req.setAttribute("city",city);
        HttpSession session = req.getSession();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Cookie c = new Cookie("city" + city.getId(), city.getName());
        c.setDomain("localhost");
        c.setPath("/");
        resp.addCookie(c);
        try {
            resp.setContentType("text/html;charset=utf8");
            req.getRequestDispatcher("success.jsp").forward(req,resp);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
