package com.byd.gzq.servlet;

import com.byd.gzq.bean.City;
import com.byd.gzq.dao.CityDao;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CityServlet extends BaseServlet{

    public void retrieveCity(HttpServletRequest req, HttpServletResponse resp){
        String cid = req.getParameter("cid");
        City city = null;
        CityDao cityDao = new CityDao();
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
        try {
            req.getRequestDispatcher("success.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
