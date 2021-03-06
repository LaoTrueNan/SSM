package com.byd.gzq.servlet;

import com.byd.gzq.listener.GlobalListener;
import com.byd.gzq.utils.CommonUtils;
import com.byd.gzq.utils.DBUtils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class BaseServlet extends HttpServlet{

    protected ApplicationContext ioc;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        if(CommonUtils.isStaticReq(req)){
            try {
//                resp.sendRedirect("error.html");
                req.setAttribute("url",req.getRequestURI());
                req.getRequestDispatcher("error.jsp").forward(req,resp);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }
        String[] uri = req.getRequestURI().split("/");
        String target = uri[uri.length - 1];
        try {
            Method handler = this.getClass().getDeclaredMethod(target, HttpServletRequest.class, HttpServletResponse.class);
            handler.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            try {
                req.setAttribute("url",req.getRequestURI());
                req.getRequestDispatcher("404.jsp").forward(req,resp);
            } catch (ServletException servletException) {
                servletException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //fixme ????????????servlet?????????ApplicationContext??????
        ioc = GlobalListener.getIoc();
        super.init(config);
    }
}
